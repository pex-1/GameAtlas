package com.example.core.network.genre

import kotlinx.serialization.Serializable

@Serializable
data class GenreResponse (
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<GenreDto>
)