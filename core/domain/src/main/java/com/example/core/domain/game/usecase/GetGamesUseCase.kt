package com.example.core.domain.game.usecase

import androidx.paging.PagingData
import com.example.core.domain.GameRepository
import com.example.core.domain.game.Game
import kotlinx.coroutines.flow.Flow

class GetGamesUseCase(
    private val repository: GameRepository
) {
    operator fun invoke(): Flow<PagingData<Game>> {
        return repository.getPagedGames()
    }
}
