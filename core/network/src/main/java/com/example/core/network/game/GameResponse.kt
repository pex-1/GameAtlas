package com.example.core.network.game

import kotlinx.serialization.Serializable

@Serializable
data class GameResponse (
    val count: Int,
    val next: String?,
    val results: List<GameDto>
)