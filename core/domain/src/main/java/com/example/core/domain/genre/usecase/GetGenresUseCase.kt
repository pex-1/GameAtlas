package com.example.core.domain.genre.usecase

import com.example.core.domain.GameRepository
import com.example.core.domain.genre.Genre

class GetGenresUseCase(
    private val repository: GameRepository
) {
    suspend operator fun invoke(): List<Genre> {
        return repository.getGenres()
    }
}