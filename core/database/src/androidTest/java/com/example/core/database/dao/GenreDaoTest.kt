package com.example.core.database.dao

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.hasSize
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import com.example.core.database.PreviewData
import com.example.core.database.dao.di.testDatabaseModule
import com.example.core.database.mappers.toGenreEntity
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.test.KoinTest
import org.koin.test.inject

class GenreDaoTest : KoinTest {

    private val genreDao: GenreDao by inject()

    @Before
    fun loadModules() {
        loadKoinModules(testDatabaseModule)
    }

    @After
    fun unloadModules() {
        unloadKoinModules(testDatabaseModule)
    }

    @Test
    fun upsertGenres() = runTest {
        val size = 4
        val genres = testGenreEntity(4)

        genreDao.upsertGenres(genres)
        val storedGenres = genreDao.getGenres()

        assertThat(storedGenres).contains(genres.first())
        assertThat(storedGenres).hasSize(size)

    }

    @Test
    fun getSelectedGenres() = runTest {
        val genres = testGenreEntity(5)

        genreDao.upsertGenres(genres.map { it.copy(isSelected = genres.indexOf(it) % 2 == 0) })

        val getSelectedGenres = genreDao.getSelectedGenres()

        assertThat(getSelectedGenres.size).isEqualTo(3)
    }

    @Test
    fun deleteAllGenres() = runTest {
        val genres = testGenreEntity(10)

        genreDao.upsertGenres(genres)
        genreDao.deleteAllGenres()

        assertThat(genreDao.getGenres()).isEmpty()
    }
}

private fun testGenreEntity(
    size: Int
) = PreviewData.getGenreData(size).map { it.toGenreEntity() }