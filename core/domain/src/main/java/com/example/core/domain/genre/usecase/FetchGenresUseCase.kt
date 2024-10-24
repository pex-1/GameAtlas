package com.example.core.domain.genre.usecase

import com.example.core.domain.util.DataError
import com.example.core.domain.util.EmptyResult
import com.example.core.domain.GameRepository

class FetchGenresUseCase(
    private var repository: GameRepository
) {
    suspend operator fun invoke(genreIds: List<Int>): EmptyResult<DataError> {
        return repository.fetchGenres(genreIds)
    }
}