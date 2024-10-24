package com.example.core.android_test

import android.app.Application
import androidx.test.platform.app.InstrumentationRegistry
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class TestGameAtlasApp : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(InstrumentationRegistry.getInstrumentation().targetContext.applicationContext)
        }
    }

    override fun onTerminate() {
        stopKoin()
        super.onTerminate()
    }
}