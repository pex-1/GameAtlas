package com.example.core.domain.game

import com.example.core.domain.genre.Genre
import java.time.LocalDate

data class Game(
    val id: Int,
    val roomId: Int = 0,
    val name: String,
    val backgroundImage: String,
    val rating: Float,
    val description: String = "",
    val parentPlatforms: List<Platform> = emptyList(),
    val genres: List<Genre> = emptyList(),
    val released: LocalDate? = null,
    val screenshots: List<GameScreenshot> = emptyList()
)