package com.example.feature.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.cachedIn
import com.example.core.domain.game.usecase.GetGamesUseCase

class GamesViewModel(
    private val getGamesUseCase: GetGamesUseCase
) : ViewModel() {

    var state by mutableStateOf(GameState())
        private set

    var getAllGames = getGamesUseCase.invoke().cachedIn(viewModelScope)

    fun onAction(action: GamesAction) {
        when (action) {
            is GamesAction.IsLoadingGames -> {
                state = state.copy(isLoading = action.isLoading)
            }

            is GamesAction.OnRefresh -> {
                state = state.copy(isRefreshing = true)
            }
        }
    }

    fun handleLoadState(loadStates: LoadStates, itemCount: Int) {
        val errorLoadState = arrayOf(
            loadStates.append,
            loadStates.prepend,
            loadStates.refresh
        ).filterIsInstance<LoadState.Error>().firstOrNull()

        val throwable = errorLoadState?.error
        if (throwable != null && itemCount == 0) {
            state = state.copy(showEmptyState = true, isLoading = false)
        }
        if (itemCount > 0) {
            state = state.copy(showEmptyState = false)
        }

        state = if (loadStates.refresh is LoadState.Loading) {
            state.copy(isLoading = true)
        } else state.copy(isLoading = false)
    }
}