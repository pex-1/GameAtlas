package com.example.gameatlas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.SessionStorage
import com.example.core.domain.genre.usecase.FetchGenresUseCase
import com.example.core.domain.util.Result
import kotlinx.coroutines.launch

class MainViewModel(
    private val sessionStorage: SessionStorage,
    private val fetchGenresUseCase: FetchGenresUseCase
) : ViewModel() {

    var state by mutableStateOf(MainState())
        private set

    init {
        viewModelScope.launch {
            state = state.copy(fetchingGenres = true)
            val selectedGenres = sessionStorage.get()
            state = state.copy(hasSelectedGenres = selectedGenres.isNotEmpty())

            state = when(fetchGenresUseCase.invoke(selectedGenres)){
                is Result.Success -> {
                    state.copy(fetchingGenres = false)
                }

                is Result.Error -> {
                    state.copy(fetchingGenres = false)
                }
            }

        }
    }

}