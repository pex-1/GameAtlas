package com.example.core.domain

import com.example.core.domain.game.Game
import com.example.core.domain.genre.Genre
import com.example.core.domain.util.DataError
import com.example.core.domain.util.Result

interface RemoteDataSource {
    suspend fun fetchGames(page: Int, genreIds: List<Int>): Result<List<Game>, DataError.Network>
    suspend fun fetchGenres(): Result<List<Genre>, DataError.Network>
    suspend fun fetchGameDetails(gameId: Int): Result<Game, DataError.Network>
}