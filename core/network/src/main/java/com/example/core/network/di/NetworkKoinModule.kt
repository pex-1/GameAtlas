package com.example.core.network.di

import com.example.core.domain.RemoteDataSource
import com.example.core.network.KtorRemoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule = module {
    singleOf(::KtorRemoteDataSource).bind<RemoteDataSource>()
}