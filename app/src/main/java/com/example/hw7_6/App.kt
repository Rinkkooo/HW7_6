package com.example.hw7_6

import android.app.Application
import com.example.data.di.dataModules
import com.example.domain.di.useCaseModule
import com.example.hw7_6.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(appModule)
            modules(dataModules)
            modules(useCaseModule)
        }
    }
}