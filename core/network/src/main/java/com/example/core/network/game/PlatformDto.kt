package com.example.core.network.game

import kotlinx.serialization.Serializable

@Serializable
data class PlatformChild (
    val id: Int,
    val name: String
)

@Serializable
data class PlatformDto(
    val platform: PlatformChild
)