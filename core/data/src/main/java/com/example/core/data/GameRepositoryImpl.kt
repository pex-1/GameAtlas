package com.example.core.data

import androidx.paging.PagingData
import com.example.core.domain.GameRepository
import com.example.core.domain.RemoteDataSource
import com.example.core.domain.details.GameDetails
import com.example.core.domain.game.Game
import com.example.core.domain.LocalDataSource
import com.example.core.domain.genre.Genre
import com.example.core.domain.util.DataError
import com.example.core.domain.util.EmptyResult
import com.example.core.domain.util.Result
import com.example.core.domain.util.asEmptyDataResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow

class GameRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val applicationScope: CoroutineScope
) : GameRepository {

    override suspend fun upsertGames(games: List<Game>): EmptyResult<DataError> {
        return when (val result = localDataSource.upsertGames(games)) {
            is Result.Success -> result.asEmptyDataResult()
            is Result.Error -> Result.Error(result.error)
        }
    }

    override fun getGameById(gameId: Int): Flow<Game> {
        return localDataSource.getGameById(gameId)
    }

    override suspend fun deleteAllGames() {
        localDataSource.deleteAllGames()
    }

    override suspend fun fetchGames(page: Int): Result<List<Game>, DataError.Network> {
        return remoteDataSource.fetchGames(page, localDataSource.getSelectedGenreIds())
    }

    override suspend fun getGameCount(): Int {
        return localDataSource.getGameCount()
    }

    override suspend fun updateGameDetails(gameDetails: GameDetails) {
        localDataSource.updateGameDetails(gameDetails)
    }

    override fun getPagedGames(): Flow<PagingData<Game>> {
        return localDataSource.getPagedGames()
    }

    override suspend fun getGenres(): List<Genre> {
        return localDataSource.getGenres()
    }

    override suspend fun upsertGenres(genres: List<Genre>) {
        localDataSource.upsertGenres(genres)
    }

    override suspend fun fetchGenres(genreIds: List<Int>): EmptyResult<DataError> {
        return when (val result = remoteDataSource.fetchGenres()) {
            is Result.Success -> {
                applicationScope.async {
                    val genres = if (genreIds.isNotEmpty()) {
                        result.data.map {
                            it.copy(isSelected = it.id in genreIds)
                        }
                    } else {
                        result.data
                    }
                    localDataSource.upsertGenres(genres).asEmptyDataResult()
                }.await()
            }

            else -> result.asEmptyDataResult()
        }
    }

    override suspend fun deleteAllGenres() {
        localDataSource.deleteAllGenres()
    }

    override suspend fun fetchGameDetails(gameId: Int): Result<Game, DataError.Network> {
        return remoteDataSource.fetchGameDetails(gameId)
    }
}