package com.example.gameatlas.nav

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.feature.details.DetailsScreenRoot
import com.example.feature.genres.GenreScreenRoot
import com.example.feature.home.GamesScreenRoot

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavigationRoot(
    navController: NavHostController,
    hasSelectedGenres: Boolean
) {
    SharedTransitionLayout {

        NavHost(
            navController = navController,
            startDestination = if (hasSelectedGenres) NavigationScreens.GamesScreen else NavigationScreens.GenreOnboardingScreen
        ) {
            composable<NavigationScreens.GenreOnboardingScreen> {
                GenreScreenRoot(onContinueClick = { onboardingCompleted ->
                    navController.popBackStack()
                    if (!onboardingCompleted) {
                        navController.navigate(NavigationScreens.GamesScreen)
                    }
                },
                    onBackClick = {
                        navController.navigateUp()
                    })
            }

            composable<NavigationScreens.GamesScreen> {

                GamesScreenRoot(
                    onGenreSelectionClick = {
                        navController.navigate(NavigationScreens.GenreOnboardingScreen)
                    },
                    onGameClick = { id ->
                        navController.navigate(NavigationScreens.Details(id))
                    },
                    animatedVisibilityScope = this,
                    sharedTransitionScope = this@SharedTransitionLayout
                )
            }

            composable<NavigationScreens.Details> {
                val args = it.toRoute<NavigationScreens.Details>()
                DetailsScreenRoot(
                    args.id,
                    animatedVisibilityScope = this,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    onBackClick = { navController.navigateUp() }
                )
            }
        }
    }
}

