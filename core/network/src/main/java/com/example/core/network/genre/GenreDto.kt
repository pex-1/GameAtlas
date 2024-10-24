package com.example.core.network.genre

import kotlinx.serialization.Serializable

@Serializable
data class GenreDto(
    val games_count: Int,
    val id: Int,
    val image_background: String,
    val name: String,
    val slug: String
)