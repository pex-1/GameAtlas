package com.example.gameatlas

import android.app.Application
import android.content.Context
import com.example.core.data.di.coreDataModule
import com.example.core.database.di.databaseModule
import com.example.core.network.di.networkModule
import com.example.feature.details.di.detailsFeatureModule
import com.example.feature.genres.di.genreFeatureModule
import com.example.feature.home.di.homeFeatureModule
import com.example.gameatlas.di.appModule
import com.google.android.play.core.splitcompat.SplitCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class GameAtlasApp: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@GameAtlasApp)
            modules(
                coreDataModule,
                appModule,
                networkModule,
                databaseModule,
                genreFeatureModule,
                homeFeatureModule,
                detailsFeatureModule
            )
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        SplitCompat.install(this)
    }
}