package com.example.core.data

import android.content.SharedPreferences
import com.example.core.domain.SessionStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PreferencesSessionStorage(
    private val sharedPreferences: SharedPreferences
) : SessionStorage {

    override suspend fun get(): List<Int> {
        return withContext(Dispatchers.IO) {
            val list = sharedPreferences.getString(KEY_GENRE_INFO, null)
            list?.split(",")?.toList()?.map { it.toInt() } ?: emptyList()
        }
    }

    override suspend fun set(info: List<Int>) {
        withContext(Dispatchers.IO) {
            sharedPreferences.edit().putString(KEY_GENRE_INFO, info.joinToString(",")).commit()
        }
    }

    companion object {
        private const val KEY_GENRE_INFO = "KEY_GENRE_INFO"
    }
}