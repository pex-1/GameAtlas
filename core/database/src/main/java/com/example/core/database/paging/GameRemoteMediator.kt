package com.example.core.database.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.core.database.RoomLocalDataSource
import com.example.core.database.entity.GameEntity
import com.example.core.database.util.DatabaseConstants
import com.example.core.domain.RemoteDataSource
import com.example.core.domain.util.Result

@OptIn(ExperimentalPagingApi::class)
class GameRemoteMediator(
    private val localDataSource: RoomLocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : RemoteMediator<Int, GameEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GameEntity>
    ): MediatorResult {
        val currentPage = when (loadType) {
            LoadType.REFRESH -> {
                1
            }

            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)

            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()
                if (lastItem == null) {
                    1
                } else {
                    val gameCount = localDataSource.getGameCount()
                    val page = (gameCount + DatabaseConstants.INITIAL_LOAD_SIZE -
                            DatabaseConstants.ITEMS_PER_PAGE) / state.config.pageSize + 1
                    page
                }
            }
        }
        return try {

            when(val response = remoteDataSource.fetchGames(currentPage, localDataSource.getSelectedGenreIds())) {
                is Result.Success -> {
                    if (loadType == LoadType.REFRESH) {
                        localDataSource.deleteAllGames()
                    }

                    when(val upsertResult = localDataSource.upsertGames(response.data)) {
                        is Result.Error -> MediatorResult.Error(Throwable(upsertResult.error.toString()))
                        is Result.Success -> {
                            MediatorResult.Success(endOfPaginationReached = response.data.isEmpty())
                        }
                    }
                }
                is Result.Error -> {
                    MediatorResult.Error(Throwable(response.error.toString()))
                }
            }

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}