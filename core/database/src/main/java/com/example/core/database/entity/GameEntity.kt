package com.example.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.database.util.DatabaseConstants
import com.example.core.domain.game.GameScreenshot
import com.example.core.domain.game.Platform
import com.example.core.domain.genre.Genre
import java.time.LocalDate

@Entity(tableName = DatabaseConstants.GAME_TABLE)
data class GameEntity(
    //game id doesn't match the order they are fetched
    //auto generated key is used to keep the original order
    @PrimaryKey(autoGenerate = true)
    val roomId: Int,
    val id: Int,
    val name: String,
    val backgroundImage: String,
    val description: String,
    val rating: Float,
    val parentPlatforms: List<Platform>,
    val genres: List<Genre>,
    val released: LocalDate?,
    val screenshots: List<GameScreenshot>
)