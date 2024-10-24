package com.example.feature.home

interface GamesAction {
    data object OnGenreSelectionClick : GamesAction
    data class OnGameClick(val gameId: Int, val name: String) : GamesAction
    data class IsLoadingGames(val isLoading: Boolean) : GamesAction
    data object OnRefresh : GamesAction
}