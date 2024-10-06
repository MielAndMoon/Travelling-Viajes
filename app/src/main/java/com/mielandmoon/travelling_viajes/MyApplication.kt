package com.mielandmoon.travelling_viajes

import android.app.Application
import com.mielandmoon.travelling_viajes.auth.authModule
import com.mielandmoon.travelling_viajes.core.appModule
import com.mielandmoon.travelling_viajes.destination.destinationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule, authModule, destinationModule)
        }
    }
}