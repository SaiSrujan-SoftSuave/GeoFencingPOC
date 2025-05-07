package dev.demo.kmp

import android.app.Application
import org.koin.android.ext.koin.androidContext

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin() {
            androidContext(this@MainApplication)
        }
    }
}