package dev.koga.cmptutorial

import android.app.Application

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppMultiplatform.initialize()
    }
}