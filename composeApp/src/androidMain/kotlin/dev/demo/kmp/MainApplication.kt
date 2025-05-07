package dev.demo.kmp

import android.app.Application

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}