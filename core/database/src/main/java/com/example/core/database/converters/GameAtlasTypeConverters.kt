package com.example.core.database.converters

import androidx.room.TypeConverter
import com.example.core.domain.game.GameScreenshot
import com.example.core.domain.game.Platform
import com.example.core.domain.genre.Genre
import com.example.core.domain.util.dateToString
import com.example.core.domain.util.toDate
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.time.LocalDate

open class GameAtlasTypeConverters {
    @TypeConverter
    fun toGenre(jsonData: String): List<Genre> {
        return Json.decodeFromString<List<Genre>>(jsonData)
    }

    @TypeConverter
    fun fromGenre(genres: List<Genre>): String {
        return Json.encodeToString(genres)
    }

    @TypeConverter
    fun toPlatform(jsonData: String): List<Platform> {
        return Json.decodeFromString<List<Platform>>(jsonData)
    }

    @TypeConverter
    fun fromPlatform(platforms: List<Platform>): String {
        return Json.encodeToString(platforms)
    }

    @TypeConverter
    fun toScreenshot(jsonData: String): List<GameScreenshot> {
        return Json.decodeFromString<List<GameScreenshot>>(jsonData)
    }

    @TypeConverter
    fun fromScreenshot(screenshots: List<GameScreenshot>): String {
        return Json.encodeToString(screenshots)
    }

    @TypeConverter
    fun toDate(dateString: String): LocalDate? {
        return dateString.toDate()
    }

    @TypeConverter
    fun fromDate(date: LocalDate?): String? {
        return date?.dateToString()
    }

}