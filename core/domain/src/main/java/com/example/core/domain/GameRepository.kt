package com.example.core.domain

import androidx.paging.PagingData
import com.example.core.domain.details.GameDetails
import com.example.core.domain.game.Game
import com.example.core.domain.genre.Genre
import com.example.core.domain.util.DataError
import com.example.core.domain.util.EmptyResult
import com.example.core.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    suspend fun upsertGames(games: List<Game>): EmptyResult<DataError>
    fun getGameById(gameId: Int): Flow<Game>
    suspend fun fetchGames(page: Int): Result<List<Game>, DataError.Network>
    suspend fun getGameCount(): Int
    suspend fun updateGameDetails(gameDetails: GameDetails)
    suspend fun deleteAllGames()
    fun getPagedGames(): Flow<PagingData<Game>>

    suspend fun getGenres(): List<Genre>
    suspend fun upsertGenres(genres: List<Genre>)
    suspend fun fetchGenres(genreIds: List<Int>): EmptyResult<DataError>
    suspend fun deleteAllGenres()

    suspend fun fetchGameDetails(gameId: Int): Result<Game, DataError.Network>
}