package com.example.core.database

import android.database.sqlite.SQLiteFullException
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.core.database.entity.GameEntity
import com.example.core.domain.details.GameDetails
import com.example.core.domain.game.Game
import com.example.core.domain.LocalDataSource
import com.example.core.domain.genre.Genre
import com.example.core.domain.util.DataError
import com.example.core.domain.util.Result
import com.example.core.database.entity.GenreEntity
import com.example.core.database.mappers.toGame
import com.example.core.database.mappers.toGameEntity
import com.example.core.database.mappers.toGenre
import com.example.core.database.mappers.toGenreEntity
import com.example.core.database.paging.GameRemoteMediator
import com.example.core.database.util.DatabaseConstants
import com.example.core.domain.GameId
import com.example.core.domain.GenreId
import com.example.core.domain.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomLocalDataSource(
    private val gamesDatabase: GamesDatabase,
    private val remoteDataSource: RemoteDataSource
) : LocalDataSource {

    private val gameDao = gamesDatabase.gameDao
    private val genreDao = gamesDatabase.genreDao

    override suspend fun upsertGames(games: List<Game>): Result<List<GameId>, DataError.Local> {
        return try {
            val entities = games.map(Game::toGameEntity)
            gameDao.upsertGames(entities)
            Result.Success(entities.map { it.id })
        } catch (e: SQLiteFullException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override fun getGameById(gameId: Int): Flow<Game> {
        return gameDao.getGameById(gameId).map { it.toGame() }
    }

    override suspend fun getGameCount(): Int {
        return gameDao.getGameCount()
    }

    override suspend fun updateGameDetails(gameDetails: GameDetails) {
        gameDao.updateGameDetails(gameDetails)
    }

    override suspend fun deleteAllGames() {
        gameDao.deleteAllGames()
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getPagedGames(): Flow<PagingData<Game>> {
        val pagingSourceFactory = { gameDao.getGames() }
        return Pager(
            config = PagingConfig(
                pageSize = DatabaseConstants.ITEMS_PER_PAGE,
                prefetchDistance = DatabaseConstants.PREFETCH_DISTANCE,
                initialLoadSize = DatabaseConstants.INITIAL_LOAD_SIZE
            ),
            remoteMediator = GameRemoteMediator(
                localDataSource = this,
                remoteDataSource = remoteDataSource
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { data ->
            data.map(GameEntity::toGame)
        }
    }

    override suspend fun getGenres(): List<Genre> {
        return genreDao.getGenres().map(GenreEntity::toGenre)
    }

    override suspend fun getSelectedGenreIds(): List<Int> {
        return genreDao.getSelectedGenres()
    }

    override suspend fun upsertGenres(genres: List<Genre>): Result<List<GenreId>, DataError.Local> {
        return try {
            val genreEntities = genres.map(Genre::toGenreEntity)
            genreDao.upsertGenres(genreEntities)
            Result.Success(genreEntities.map { it.id })
        } catch (e: SQLiteFullException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteAllGenres() {
        genreDao.deleteAllGenres()
    }
}