package com.example.core.domain.genre.usecase

data class GenreUseCase(
    val fetchGenres: FetchGenresUseCase,
    val getGenres: GetGenresUseCase,
    val upsertGenres: UpsertGenresUseCase
)
