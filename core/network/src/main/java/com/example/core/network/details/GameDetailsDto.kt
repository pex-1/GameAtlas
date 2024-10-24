package com.example.core.network.details

import com.example.core.network.game.PlatformDto
import com.example.core.network.genre.GenreDto
import kotlinx.serialization.Serializable

@Serializable
data class GameDetailsDto(
    val id: Int,
    val name: String,
    val released: String,
    val background_image: String,
    val rating: Float,
    val parent_platforms: List<PlatformDto>,
    val description_raw: String,
    val genres: List<GenreDto>
)
