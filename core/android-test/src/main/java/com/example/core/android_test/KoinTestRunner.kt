package com.example.core.android_test

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class KoinTestRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): Application {
        return super.newApplication(cl, TestGameAtlasApp::class.java.name, context)
    }
}