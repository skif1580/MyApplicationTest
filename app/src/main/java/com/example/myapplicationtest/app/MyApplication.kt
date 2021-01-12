package com.example.myapplicationtest.app

import android.app.Application
import com.example.myapplicationtest.di.detailsModule
import com.example.myapplicationtest.di.moviesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(listOf(moviesModule, detailsModule))
        }
    }
}