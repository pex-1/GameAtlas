package com.example.feature.genres

sealed interface GenreAction {
    data object OnContinueClick : GenreAction
    data object OnBackClick : GenreAction
    data class OnGenreSelectedClick(val genreIndex: Int) : GenreAction
    data object OnRetryClick : GenreAction
}