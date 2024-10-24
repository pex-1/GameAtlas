package com.example.core.database.dao

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import com.example.core.database.PreviewData
import com.example.core.database.dao.di.testDatabaseModule
import com.example.core.database.mappers.toGameEntity
import com.example.core.domain.details.GameDetails
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.test.KoinTest
import org.koin.test.inject

class GameDaoTest : KoinTest {

    private val gameDao: GameDao by inject()

    @Before
    fun loadModules() {
        loadKoinModules(testDatabaseModule)
    }

    @After
    fun unloadModules() {
        unloadKoinModules(testDatabaseModule)
    }

    @Test
    fun getGamesById() = runTest {
        val games = testGameEntity()
        gameDao.upsertGames(games)

        val firstId = games.first().id

        assertThat(gameDao.getGameById(firstId).firstOrNull()).isNotNull()
    }

    @Test
    fun getGameCount() = runTest {
        val games = testGameEntity()
        gameDao.upsertGames(games)

        assertThat(gameDao.getGameCount()).isEqualTo(games.size)
    }

    @Test
    fun updateGameDetails() = runTest {
        val game = testGameEntity().first()
        val gameDescription = PreviewData.getGameDescription()

        gameDao.upsertGames(listOf(game))
        val gameDetails = GameDetails(gameDao.getGameById(game.id).first().roomId, gameDescription)
        gameDao.updateGameDetails(gameDetails)

        assertThat(gameDao.getGameById(game.id).first().description).isEqualTo(gameDescription)
    }

    @Test
    fun deleteAllGames() = runTest {
        val games = testGameEntity()
        gameDao.upsertGames(games)
        gameDao.deleteAllGames()

        assertThat(gameDao.getGameCount()).isEqualTo(0)
    }
}

private fun testGameEntity() = PreviewData.getGamesData().map { it.toGameEntity() }