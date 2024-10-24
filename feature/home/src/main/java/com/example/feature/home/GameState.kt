package com.example.feature.home

data class GameState (
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val showEmptyState: Boolean = false
)