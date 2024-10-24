package com.example.core.network.genre

import com.example.core.domain.genre.Genre

fun GenreDto.toGenre(): Genre {
    return Genre(
        gameCount = games_count,
        id = id,
        imageBackground = image_background,
        name = name
    )
}