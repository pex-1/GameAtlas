package com.example.core.domain.genre

import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    val id: Int,
    val name: String,
    val imageBackground: String = "",
    val gameCount: Int = -1,
    val isSelected: Boolean = false
)