package com.example.core.database.mappers

import com.example.core.domain.game.Game
import com.example.core.domain.genre.Genre
import com.example.core.database.entity.GameEntity
import com.example.core.database.entity.GenreEntity

fun GameEntity.toGame(): Game {
    return Game(
        id = id,
        roomId = roomId,
        name = name,
        backgroundImage = backgroundImage,
        rating = rating,
        description = description,
        parentPlatforms = parentPlatforms,
        genres = genres,
        released = released,
        screenshots = screenshots,
    )
}

fun Game.toGameEntity(): GameEntity {
    return GameEntity(
        id = id,
        roomId = roomId,
        name = name,
        backgroundImage = backgroundImage,
        rating = rating,
        released = released,
        description = description,
        parentPlatforms = parentPlatforms,
        genres = genres,
        screenshots = screenshots,
    )
}

fun GenreEntity.toGenre(): Genre {
    return Genre(
        id = id,
        name = name,
        gameCount = gameCount,
        imageBackground = imageBackground,
        isSelected = isSelected
    )
}