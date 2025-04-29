package com.example.feature.details

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import com.example.core.domain.game.Game

data class DetailsState(
    val game: Game? = null,
    val description: String = "",
    val scrollEnabled: Boolean = false,
    val animationsEnabled: Boolean = true,
    val offsetAnimation: Animatable<Float, AnimationVector1D> = Animatable(100f),
    val fadeAnimation: Animatable<Float, AnimationVector1D> = Animatable(0f)
)