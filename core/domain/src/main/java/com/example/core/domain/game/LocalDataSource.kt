package com.example.core.domain.game

import androidx.paging.PagingData
import com.example.core.domain.details.GameDetails
import com.example.core.domain.genre.Genre
import com.example.core.domain.util.DataError
import com.example.core.domain.util.Result
import kotlinx.coroutines.flow.Flow

typealias GameId = Int
typealias GenreId = Int

interface LocalDataSource {

    suspend fun upsertGames(games: List<Game>): Result<List<GameId>, DataError.Local>
    fun getGameById(gameId: Int): Flow<Game>
    suspend fun getGameCount(): Int
    suspend fun updateGameDetails(gameDetails: GameDetails)
    suspend fun deleteAllGames()
    fun getPagedGames(): Flow<PagingData<Game>>

    suspend fun getGenres(): List<Genre>
    suspend fun getSelectedGenreIds(): List<Int>
    suspend fun upsertGenres(genres: List<Genre>): Result<List<GenreId>, DataError.Local>
    suspend fun deleteAllGenres()
}