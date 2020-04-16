package com.chisw.githubuserssearch.presentation.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        init()
    }

    private fun init() {
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidContext(this@App)
            modules(KoinModules.getModules())
        }
    }

}