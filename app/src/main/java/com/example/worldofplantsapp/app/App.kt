package com.example.worldofplantsapp.app

import android.app.Application
import com.example.worldofplantsapp.utils.view.model.Prefs
import dagger.hilt.android.HiltAndroidApp

val prefs: Prefs by lazy {
    App.prefs!!
}

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        prefs = Prefs(applicationContext)

    }

    companion object {
        var prefs: Prefs? = null
        lateinit var instance: App
            private set

    }
}