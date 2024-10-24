package com.example.core.domain.game.usecase

import com.example.core.domain.GameRepository

class DeleteGamesUseCase (
    private val repository: GameRepository
) {
    suspend operator fun invoke() {
        repository.deleteAllGames()
    }
}