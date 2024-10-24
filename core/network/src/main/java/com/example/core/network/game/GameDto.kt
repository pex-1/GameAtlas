package com.example.core.network.game

import com.example.core.domain.genre.Genre
import kotlinx.serialization.Serializable

@Serializable
class GameDto (
    val id: Int,
    val name: String,
    val background_image: String?,
    val rating: Float,
    val parent_platforms: List<PlatformDto>,
    val genres: List<Genre>,
    val released: String?,
    val short_screenshots: List<ScreenshotDto>
)