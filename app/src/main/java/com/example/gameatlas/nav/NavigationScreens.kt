package com.example.gameatlas.nav

import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationScreens {
    @Serializable
    data object GenreOnboardingScreen: NavigationScreens()
    @Serializable
    data object GamesScreen: NavigationScreens()
    @Serializable
    data class Details(
        val id: Int
    ): NavigationScreens()
}