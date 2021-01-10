package br.com.loboneto.heroes.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Main : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
