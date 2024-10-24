package com.example.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.core.database.entity.GenreEntity

@Dao
interface GenreDao {

    @Query("SELECT * FROM genre_table")
    suspend fun getGenres(): List<GenreEntity>

    @Query("SELECT id FROM genre_table WHERE isSelected = 1")
    suspend fun getSelectedGenres(): List<Int>

    @Upsert
    suspend fun upsertGenres(genres: List<GenreEntity>)

    @Query("DELETE FROM genre_table")
    suspend fun deleteAllGenres()
}