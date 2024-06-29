package com.example.worldofplantsapp.app

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.app.NotificationCompat
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.worldofplantsapp.R
import com.example.worldofplantsapp.utils.theme.WorldOfPlantsAppTheme
import com.example.worldofplantsapp.utils.view.model.ThemeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val themeViewModel: ThemeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        createNotificationChannel(this)
        setContent {
            val themeDark = prefs.themeDark
            themeDark.let {
                themeViewModel.setTheme(it)
            }
            WorldOfPlantsAppTheme(darkTheme = themeViewModel.isDarkThemeEnabled.value) {
                val viewModel: PlantsMainAppViewModel = hiltViewModel()
                PlantsMainApp(
                    viewModel.destinationFlow,
                    themeViewModel = themeViewModel,
                    onNavigaty = { video ->
                        val url = "https://www.youtube.com/results?search_query=$video"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        startActivity(intent)
                    },
                    onNavigatyMap = { map ->
                        val url = "http://maps.google.com/maps?q=$map"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        startActivity(intent)
                    },
                    context = applicationContext
                )
            }
        }
    }
}

fun createNotificationChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "Reminder Channel"
        val descriptionText = "Channel for medicine reminders"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("reminder_channel", name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

fun scheduleReminder(context: Context, medicineName: String, time: Calendar) {
    val delay = time.timeInMillis - System.currentTimeMillis()
    val workRequest = OneTimeWorkRequestBuilder<ReminderWorker>()
        .setInitialDelay(delay, TimeUnit.MILLISECONDS)
        .setInputData(workDataOf("medicineName" to medicineName))
        .build()
    WorkManager.getInstance(context).enqueue(workRequest)
}

class ReminderWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        val medicineName = inputData.getString("medicineName") ?: return Result.failure()
        showNotification(medicineName)
        return Result.success()
    }

    private fun showNotification(medicineName: String) {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(applicationContext, "reminder_channel")
            .setContentTitle("Напоминание о поливе Растения")
            .setContentText("Пора Полить $medicineName")
            .setSmallIcon(R.drawable.image)
            .build()
        notificationManager.notify(0, notification)
    }
}