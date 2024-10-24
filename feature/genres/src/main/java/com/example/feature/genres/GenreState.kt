package com.example.feature.genres

import com.example.core.domain.genre.Genre

data class GenreState (
    val genres: List<Genre> = emptyList(),
    val selected: List<Int> = emptyList(),
    val isLoading: Boolean = false,
    val canContinue: Boolean = false,
    val onboardingCompleted: Boolean = false,
    val showEmptyState: Boolean = false
)