package com.example.sbtechincaltest.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PhotoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //setting up koin modules for dependency injection to use dependencies provided by the modules throughout the application
        startKoin {
            androidContext(this@PhotoApplication)
            modules(listOf(networkModule, viewModelModule))
        }
    }
}