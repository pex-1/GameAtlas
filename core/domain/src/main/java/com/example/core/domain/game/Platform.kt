package com.example.core.domain.game

import com.example.core.domain.enums.PlatformType
import kotlinx.serialization.Serializable

@Serializable
data class Platform(
    val id: Int,
    val platformType: PlatformType
)
