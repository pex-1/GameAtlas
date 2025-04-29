package com.example.core.database.dao.di

import androidx.room.Room
import com.example.core.domain.LocalDataSource
import com.example.core.database.GamesDatabase
import com.example.core.database.RoomLocalDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val testDatabaseModule = module {
    single {
        Room.inMemoryDatabaseBuilder(
            androidApplication(),
            GamesDatabase::class.java
        ).build()
    }
    single { get<GamesDatabase>().gameDao }
    single { get<GamesDatabase>().genreDao }

    singleOf(::RoomLocalDataSource).bind<LocalDataSource>()
}