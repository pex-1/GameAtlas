package com.example.core.domain.game

import kotlinx.serialization.Serializable

@Serializable
data class GameScreenshot(
    val id: Int,
    val imageUrl: String
)
