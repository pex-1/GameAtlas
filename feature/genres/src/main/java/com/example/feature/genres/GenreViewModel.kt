package com.example.feature.genres

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.SessionStorage
import com.example.core.domain.util.Result
import com.example.core.domain.game.usecase.DeleteGamesUseCase
import com.example.core.domain.genre.usecase.GenreUseCase
import kotlinx.coroutines.launch

class GenreViewModel(
    private val genreUseCase: GenreUseCase,
    private val deleteGamesUseCase: DeleteGamesUseCase,
    private val sessionStorage: SessionStorage,
) : ViewModel() {

    var state by mutableStateOf(GenreState())
        private set

    init {
        viewModelScope.launch {
            state = state.copy(isLoading = true, selected = sessionStorage.get())

            if (state.selected.isNotEmpty()) {
                state = state.copy(onboardingCompleted = true)
            }
            val genres = genreUseCase.getGenres.invoke()
            state = state.copy(genres = genres, isLoading = false, showEmptyState = genres.isEmpty())
        }

    }

    fun onAction(action: GenreAction) {
        when (action) {
            is GenreAction.OnGenreSelectedClick -> {
                val genres = state.genres.mapIndexed { j, genreItem ->
                    if (action.genreIndex == j) {
                        genreItem.copy(isSelected = !genreItem.isSelected)
                    } else {
                        genreItem
                    }
                }
                val selectedGenres = genres.filter { it.isSelected }
                state = state.copy(genres = genres,
                    canContinue = selectedGenres.size > 2 &&
                            state.selected != selectedGenres.map { it.id })
            }

            is GenreAction.OnRetryClick -> {
                state = state.copy(isLoading = true)
                viewModelScope.launch {
                    state = when (genreUseCase.fetchGenres.invoke(state.selected)) {
                        is Result.Error -> {
                            state.copy(isLoading = false, showEmptyState = true)
                        }
                        is Result.Success -> {
                            state.copy(
                                isLoading = false,
                                showEmptyState = false,
                                genres = genreUseCase.getGenres.invoke()
                            )
                        }
                    }
                }
            }

            is GenreAction.OnContinueClick -> {
                viewModelScope.launch {
                    sessionStorage.set(state.genres.filter { it.isSelected }.map { it.id })
                    genreUseCase.upsertGenres.invoke(state.genres)
                    if (state.onboardingCompleted) {
                        deleteGamesUseCase.invoke()
                    }
                }
            }

            else -> {}
        }
    }

}