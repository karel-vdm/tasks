package com.ordercloud.virginactivetasks

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class VirginActiveTaskApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@VirginActiveTaskApp)
            modules(taskModule)
        }
    }
}