package com.example.core.domain.genre.usecase

import com.example.core.domain.GameRepository
import com.example.core.domain.genre.Genre

class UpsertGenresUseCase(
    private val repository: GameRepository
) {
    suspend operator fun invoke(genres: List<Genre>) {
        repository.upsertGenres(genres)
    }
}