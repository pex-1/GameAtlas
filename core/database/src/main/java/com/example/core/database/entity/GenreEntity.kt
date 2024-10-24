package com.example.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.database.util.DatabaseConstants

@Entity(tableName = DatabaseConstants.GENRE_TABLE)
data class GenreEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val gameCount: Int,
    val imageBackground: String,
    val isSelected: Boolean = false
)
