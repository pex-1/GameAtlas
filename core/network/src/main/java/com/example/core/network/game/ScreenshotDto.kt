package com.example.core.network.game

import kotlinx.serialization.Serializable

@Serializable
data class ScreenshotDto(
    val id: Int,
    val image: String
)
