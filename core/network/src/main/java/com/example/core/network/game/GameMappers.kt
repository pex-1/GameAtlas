package com.example.core.network.game

import com.example.core.domain.enums.PlatformType
import com.example.core.domain.game.Game
import com.example.core.domain.game.GameScreenshot
import com.example.core.domain.game.Platform
import com.example.core.domain.util.toDate
import com.example.core.network.details.GameDetailsDto
import com.example.core.network.genre.GenreDto
import com.example.core.network.genre.toGenre

fun GameDto.toGame(): Game {
    return Game(
        id = id,
        name = name,
        backgroundImage = background_image?: "",
        rating = rating,
        released = released?.toDate(),
        genres = genres,
        parentPlatforms = parent_platforms.map { it.platform.toGamePlatform() },
        screenshots = short_screenshots.map (ScreenshotDto::toGameScreenshot),
    )
}

fun GameDetailsDto.toGame(): Game {
    return Game(
        id = id,
        name = name,
        backgroundImage = background_image,
        rating = rating,
        description = description_raw,
        released = released.toDate(),
        genres = genres.map (GenreDto::toGenre),
        parentPlatforms = parent_platforms.map { it.platform.toGamePlatform() },
    )
}

fun PlatformChild.toGamePlatform(): Platform {
    return Platform(
        id = id,
        platformType = when {
            name.contains("playstation", ignoreCase = true) -> PlatformType.PLAYSTATION
            name.contains("xbox", ignoreCase = true) -> PlatformType.XBOX
            name.contains("windows", ignoreCase = true) || name.contains("pc", ignoreCase = true) -> PlatformType.WINDOWS
            name.contains("wii", ignoreCase = true) -> PlatformType.WII
            name.contains("nintendo", ignoreCase = true) -> PlatformType.NINTENDO_SWITCH
            name.contains("android", ignoreCase = true) -> PlatformType.ANDROID
            name.contains("mac", ignoreCase = true) || name.contains("ios", ignoreCase = true) -> PlatformType.APPLE
            name.contains("linux", ignoreCase = true) -> PlatformType.LINUX
            else -> PlatformType.OTHER
        },
    )
}

fun ScreenshotDto.toGameScreenshot(): GameScreenshot {
    return GameScreenshot(
        id = id,
        imageUrl = image
    )
}

