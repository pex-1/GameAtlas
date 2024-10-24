package com.example.core.database

import com.example.core.domain.enums.PlatformType
import com.example.core.domain.game.Game
import com.example.core.domain.game.Platform
import com.example.core.domain.genre.Genre
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object PreviewData {

    private val platforms = listOf(
        Platform(1, PlatformType.WII),
        Platform(2, PlatformType.PLAYSTATION),
        Platform(3, PlatformType.APPLE),
        Platform(4, PlatformType.LINUX),
        Platform(5, PlatformType.XBOX),
        Platform(6, PlatformType.NINTENDO_SWITCH),
        Platform(7, PlatformType.WINDOWS),
        Platform(8, PlatformType.ANDROID),
        Platform(9, PlatformType.OTHER),
    )

    private val genres = listOf(
        Genre(1, "Racing", "", 24884),
        Genre(2, "RPG", "", 67805),
        Genre(3, "Shooter", "", 5395),
        Genre(4, "Fighting", "", 100790),
        Genre(5, "Adventure", "", 23536),
        Genre(6, "Puzzle", "", 5427),
        Genre(7, "Action", "", 56426),
        Genre(8, "Strategy", "", 4677),
        Genre(9, "Arcade", "", 433466),
        Genre(10, "Family", "", 453),
        Genre(11, "Simulation", "", 46),
        Genre(12, "Board Games", "", 6462),
        Genre(13, "Sports", "", 4478),
        Genre(14, "Educational", "", 2757),
        Genre(15, "Card", "", 7525),
        Genre(16, "Casual", "", 2575),
        Genre(17, "Indie", "", 5757),
        Genre(18, "Massively Multiplayer", "", 5727),
        Genre(19, "Platformer", "", 9975),
    )


    private val games = listOf(
        Game(
            1,
            1,
            "The Witcher 3: Wild Hunt",
            "",
            4.5f,
            "",
            platforms.shuffled().take((1..9).random()),
            genres.shuffled().take((1..19).random()),
            getDate(),
            emptyList()
        ),
        Game(
            2,
            2,
            "Grand Teft Auto V",
            "",
            4.5f,
            "",
            platforms.shuffled().take((1..9).random()),
            genres.shuffled().take((1..19).random()),
            getDate(),
            emptyList()
        ),
        Game(
            3,
            3,
            "Portal 2",
            "",
            4.5f,
            "",
            platforms.shuffled().take((1..9).random()),
            genres.shuffled().take((1..19).random()),
            getDate(),
            emptyList()
        ),
        Game(
            4,
            4,
            "Counter-Strike: Global Offensive",
            "",
            4.5f,
            "",
            platforms.shuffled().take((1..9).random()),
            genres.shuffled().take((1..19).random()),
            getDate(),
            emptyList()
        ),
        Game(
            5,
            5,
            "Tomb Raider (2013)",
            "",
            4.5f,
            "",
            platforms.shuffled().take((1..9).random()),
            genres.shuffled().take((1..19).random()),
            getDate(),
            emptyList()
        ),
        Game(
            6,
            6,
            "The Witcher",
            "",
            4.5f,
            "",
            platforms.shuffled().take((1..9).random()),
            genres.shuffled().take((1..19).random()),
            getDate(),
            emptyList()
        ),
        Game(
            7,
            7,
            "Left 4 Dead 2",
            "",
            4.5f,
            "",
            platforms.shuffled().take((1..9).random()),
            genres.shuffled().take((1..19).random()),
            getDate(),
            emptyList()
        ),
        Game(
            8,
            8,
            "The Elder Scrolls V: Skyrim",
            "",
            4.5f,
            "",
            platforms.shuffled().take((1..9).random()),
            genres.shuffled().take((1..19).random()),
            getDate(),
            emptyList()
        )
    )

    private val gameDescriptionLong =
        "A cinematic revival of the series in its action third person form, Tomb Rider follows Lara in her least experience period of life – her youth. Heavily influenced by Naughty Dog’s “Uncharted”, the game is a mix of everything, from stealth and survival to combat and QTE action scenes.\\r\\nYoung Lara Croft arrives on the Yamatai, lost island near Japan, as the leader of the expedition in search of the Yamatai Kingdom, with a diverse team of specialists. But shipwreck postponed the successful arrival and seemingly forgotten island is heavily populated with hostile inhabitants, cultists of Solarii Brotherhood.\\r\\nThe game will be graphic at times, especially after failed QTE’s during some of the survival scenes, but overall players will enjoy classic action adventure, reminiscent of the beginning of the series. This game is not a direct sequel or continuation of existing sub-series within the franchise, but a reboot, setting up Tomb Raider to represent modern gaming experience.\\r\\nThe game has RPG elements and has a world, which you can explore during the story campaign and after the completion. As well as multiplayer mode, where 2 teams (4 scavengers and 4 survivors) are clashing in 3 game modes while using weapons and environments from the single-player campaign."

    private val gameDescriptionShort =
        "A cinematic revival of the series in its action third person form, Tomb Rider follows Lara in her least experience period of life – her youth."

    fun getGenreData(genreSize: Int = genres.size): List<Genre> {
        return genres.shuffled().take(genreSize)
    }

    fun getGameDescription(long: Boolean = true): String {
        return if (long) gameDescriptionLong else gameDescriptionShort
    }

    fun getGameData(platformSize: Int, genreSize: Int): Game {
        return Game(
            5, 10, "The Witcher", "", 4.5f, "",
            platforms.take(platformSize), genres.take(genreSize), getDate(), emptyList()
        )
    }

    fun getGamesData(): List<Game> {
        return games
    }

    private fun getDate(): LocalDate? {
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        return LocalDate.parse("31.10.2021", formatter)
    }
}