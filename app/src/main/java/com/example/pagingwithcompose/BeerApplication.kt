package com.example.pagingwithcompose

import android.app.Application
import com.example.pagingwithcompose.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class BeerApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@BeerApplication)
            modules(appModule)
        }
    }
}