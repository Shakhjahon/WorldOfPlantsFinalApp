package com.example.worldofplantsapp.presentation.main.screens.plant.screen

import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.mediconsultfinalapp.ui.theme.dp10
import com.example.mediconsultfinalapp.ui.theme.dp12
import com.example.mediconsultfinalapp.ui.theme.dp5
import com.example.mediconsultfinalapp.ui.theme.dp7
import com.example.mediconsultfinalapp.ui.theme.sp17
import com.example.worldofplantsapp.app.createNotificationChannel
import com.example.worldofplantsapp.app.scheduleReminder
import com.example.worldofplantsapp.utils.theme.GreensTime
import java.util.Calendar

@Composable
fun ReminderScreen(
    context: Context = LocalContext.current, modifier: Modifier = Modifier
) {
    val sharedPreferencesManager = SharedPreferencesManager(context)
    var reminders by remember { mutableStateOf(sharedPreferencesManager.getReminders()) }
    var medicineName by remember { mutableStateOf("") }
    val time = remember { Calendar.getInstance() }

    LaunchedEffect(Unit) {
        createNotificationChannel(context)
    }

    fun saveReminder(name: String, calendar: Calendar) {
        val newReminder = name to calendar
        reminders = reminders + newReminder
        sharedPreferencesManager.saveReminders(reminders)
        scheduleReminder(context, name, calendar)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Напоминания о Поливе",
            fontSize = sp17,
            fontWeight = FontWeight.Bold,
            color = GreensTime
        )
        Text(
            text = "Напишите ещё раз",
            fontSize = sp17,
            fontWeight = FontWeight.Bold,
            color = GreensTime
        )


        OutlinedTextField(
            value = medicineName,
            onValueChange = { medicineName = it },
            placeholder = { Text("Название растения") },
            modifier = modifier
                .fillMaxWidth()
                .padding(top = dp7),
            shape = RoundedCornerShape(dp12),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = GreensTime,
                unfocusedBorderColor = GreensTime
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Button(
            modifier = modifier
                .padding(top = dp10),
            shape = RoundedCornerShape(dp12),
            colors = ButtonDefaults.buttonColors(
                containerColor = GreensTime
            ),
            onClick = {
                val currentHour = time.get(Calendar.HOUR_OF_DAY)
                val currentMinute = time.get(Calendar.MINUTE)
                TimePickerDialog(context, { _, hour: Int, minute: Int ->
                    time.set(Calendar.HOUR_OF_DAY, hour)
                    time.set(Calendar.MINUTE, minute)
                }, currentHour, currentMinute, true).show()
            },
        ) {
            Text("Выбрать время")
        }

        Button(
            modifier = modifier.padding(top = dp5),
            shape = RoundedCornerShape(dp12),
            onClick = { saveReminder(medicineName, time) },
            colors = ButtonDefaults.buttonColors(
                containerColor = GreensTime
            ),
        ) {
            Text("Сохранить напоминание")
        }
    }
}

