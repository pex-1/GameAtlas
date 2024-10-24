package com.example.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.core.database.converters.GameAtlasTypeConverters
import com.example.core.database.dao.GameDao
import com.example.core.database.dao.GenreDao
import com.example.core.database.entity.GameEntity
import com.example.core.database.entity.GenreEntity

@TypeConverters(GameAtlasTypeConverters::class)
@Database(
    entities = [
        GameEntity::class,
        GenreEntity::class,
    ],
    version = 1
)
abstract class GamesDatabase : RoomDatabase() {

    abstract val gameDao: GameDao
    abstract val genreDao: GenreDao

}