package com.example.core.presentation.designsystem.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun InitialFadeIn(
    durationMs: Int = 1000,
    delay: Long = 100,
    content: @Composable() AnimatedVisibilityScope.() -> Unit
) {
    var visibility by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = Unit, block = {
        visibility = true
        delay(delay)
    })
    AnimatedVisibility(visible = visibility, enter = fadeIn(tween(durationMs)), content = content)
}

@Composable
fun fadeInAnimation(
    durationMs: Int = ANIMATION_DURATION,
    delay: Int = ANIMATION_DELAY,
): Float {
    val animatedAlpha = remember {
        Animatable(0f)
    }
    LaunchedEffect(true) {
        animatedAlpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = durationMs,
                    easing = FastOutLinearInEasing,
                    delayMillis = delay
                )
            )
    }
    return animatedAlpha.value
}

const val ANIMATION_DELAY = 500
const val ANIMATION_DURATION = 500

@Composable
fun offsetAnimation(
    durationMs: Int = ANIMATION_DURATION,
    delay: Int = ANIMATION_DELAY,
): Dp {
    val animatedOffset = remember {
        Animatable(100f)
    }
    LaunchedEffect(key1 = true) {
        animatedOffset.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = durationMs,
                delayMillis = delay,
                easing = EaseInOut
            )
        )
    }

    return animatedOffset.value.dp
}