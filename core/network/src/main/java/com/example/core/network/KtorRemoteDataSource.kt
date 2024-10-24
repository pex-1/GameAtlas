package com.example.core.network

import com.example.core.data.BuildConfig
import com.example.core.data.HttpRoutes
import com.example.core.data.networking.get
import com.example.core.domain.RemoteDataSource
import com.example.core.domain.game.Game
import com.example.core.domain.genre.Genre
import com.example.core.domain.util.DataError
import com.example.core.domain.util.Result
import com.example.core.domain.util.map
import com.example.core.network.details.GameDetailsDto
import com.example.core.network.game.GameResponse
import com.example.core.network.game.toGame
import com.example.core.network.genre.GenreDto
import com.example.core.network.genre.GenreResponse
import com.example.core.network.genre.toGenre
import io.ktor.client.HttpClient

class KtorRemoteDataSource(
    private val client: HttpClient
): RemoteDataSource {

    override suspend fun fetchGenres(): Result<List<Genre>, DataError.Network> {
        return client.get<GenreResponse>(
            route = HttpRoutes.GENRES,
            queryParameters = mapOf(Pair(KEY, BuildConfig.API_KEY))
        ).map { genreDtos ->
            genreDtos.results.map (GenreDto::toGenre)
        }
    }

    override suspend fun fetchGameDetails(gameId: Int): Result<Game, DataError.Network> {
        return client.get<GameDetailsDto>(
            route = HttpRoutes.GAME_DETAILS.plus(gameId),
            queryParameters = mapOf(Pair(KEY, BuildConfig.API_KEY))
        ).map (GameDetailsDto::toGame)
    }

    override suspend fun fetchGames(page: Int, genreIds: List<Int>): Result<List<Game>, DataError.Network> {
        return client.get<GameResponse>(
            route = HttpRoutes.GAMES,
            queryParameters = mapOf(
                Pair(KEY, BuildConfig.API_KEY),
                Pair(PAGE, page),
                Pair(PAGE_SIZE, 20),
                Pair(GENRES, genreIds.joinToString(","))
            )
        ).map { gameDtos ->
            gameDtos.results.map { it.toGame() }
        }
    }

    companion object {
        private const val KEY = "key"
        private const val PAGE = "page"
        private const val PAGE_SIZE = "page_size"
        private const val GENRES = "genres"
    }

}