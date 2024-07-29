package org.example.news

import DI.androidModule
import DI.commonModule
import DI.initKoin
import android.app.Application
import org.koin.android.ext.koin.androidContext

/**
 * MyApplication class that extends the Application class.
 *
 * This class serves as the application-wide context and is used to initialize
 * dependency injection using Koin when the application starts.
 */
class MyApplication: Application() {

    // Override the onCreate method to initialize Koin
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MyApplication)
            modules(commonModule(), androidModule)
        }
    }
}