package com.example.core.database.di

import androidx.room.Room
import com.example.core.domain.game.LocalDataSource
import com.example.core.database.GamesDatabase
import com.example.core.database.RoomLocalDataSource
import com.example.core.database.util.DatabaseConstants
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            GamesDatabase::class.java,
            DatabaseConstants.GAME_DATABASE
        ).build()
    }
    single { get<GamesDatabase>().gameDao }
    single { get<GamesDatabase>().genreDao }

    singleOf(::RoomLocalDataSource).bind<LocalDataSource>()
}