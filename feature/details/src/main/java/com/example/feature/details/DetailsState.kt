package com.example.feature.details

import com.example.core.domain.game.Game

data class DetailsState(
    val game: Game? = null,
    val description: String = "",
    val scrollEnabled: Boolean = false,
    val animationsEnabled: Boolean = true
)