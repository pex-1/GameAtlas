package com.example.core.data.di

import com.example.core.data.GameRepositoryImpl
import com.example.core.data.PreferencesSessionStorage
import com.example.core.data.networking.HttpClientFactory
import com.example.core.domain.GameRepository
import com.example.core.domain.SessionStorage
import io.ktor.client.engine.cio.CIO
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory().build(CIO.create())
    }

    singleOf(::GameRepositoryImpl).bind<GameRepository>()

    singleOf(::PreferencesSessionStorage).bind<SessionStorage>()
}