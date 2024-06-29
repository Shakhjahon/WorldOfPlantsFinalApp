package com.example.worldofplantsapp.presentation.main.screens.plant.screen

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Calendar

class SharedPreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("reminders_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveReminders(reminders: List<Pair<String, Calendar>>) {
        val remindersData = reminders.map { it.first to it.second.timeInMillis }
        val json = gson.toJson(remindersData)
        sharedPreferences.edit().putString("reminders", json).apply()
    }

    fun getReminders(): List<Pair<String, Calendar>> {
        val json = sharedPreferences.getString("reminders", null) ?: return emptyList()
        val type = object : TypeToken<List<Pair<String, Double>>>() {}.type
        val remindersData: List<Pair<String, Double>> = gson.fromJson(json, type)
        return remindersData.map {
            it.first to Calendar.getInstance().apply { timeInMillis = it.second.toLong() }
        }
    }
}
