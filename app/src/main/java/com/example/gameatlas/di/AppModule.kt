package com.example.gameatlas.di

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.core.domain.game.usecase.DeleteGamesUseCase
import com.example.core.domain.game.usecase.GetGamesUseCase
import com.example.core.domain.genre.usecase.FetchGenresUseCase
import com.example.core.domain.genre.usecase.GenreUseCase
import com.example.core.domain.genre.usecase.GetGenresUseCase
import com.example.core.domain.genre.usecase.UpsertGenresUseCase
import com.example.gameatlas.GameAtlasApp
import com.example.gameatlas.MainViewModel
import kotlinx.coroutines.CoroutineScope
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single<SharedPreferences> {
        EncryptedSharedPreferences(
            androidApplication(),
            "prefs",
            MasterKey(androidApplication()),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    single<CoroutineScope> {
        (androidApplication() as GameAtlasApp).applicationScope
    }

    single { GetGamesUseCase(get()) }
    single { FetchGenresUseCase(get()) }
    single { GetGenresUseCase(get()) }
    single { UpsertGenresUseCase(get()) }
    single { DeleteGamesUseCase(get()) }
    single { GenreUseCase(get(), get(), get()) }

    viewModelOf(::MainViewModel)
}