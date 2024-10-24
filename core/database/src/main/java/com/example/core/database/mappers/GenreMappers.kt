package com.example.core.database.mappers

import com.example.core.domain.genre.Genre
import com.example.core.database.entity.GenreEntity

fun Genre.toGenreEntity() : GenreEntity {
    return GenreEntity(
        id = id,
        name = name,
        gameCount = gameCount,
        imageBackground = imageBackground,
        isSelected = isSelected
    )
}