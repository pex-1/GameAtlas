package com.example.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.core.database.entity.GameEntity
import com.example.core.domain.details.GameDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM game_table")
    fun getGames(): PagingSource<Int, GameEntity>

    @Query("SELECT * FROM game_table WHERE id = :gameId")
    fun getGameById(gameId: Int): Flow<GameEntity>

    @Query("SELECT COUNT(*) FROM game_table")
    suspend fun getGameCount(): Int

    @Upsert
    suspend fun upsertGames(games: List<GameEntity>)

    @Update(entity = GameEntity::class)
    suspend fun updateGameDetails(gameDetails: GameDetails)

    @Query("DELETE FROM game_table")
    suspend fun deleteAllGames()
}