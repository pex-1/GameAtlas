package com.example.feature.details

import androidx.compose.animation.core.Animatable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.GameRepository
import com.example.core.domain.details.GameDetails
import com.example.core.domain.util.Result
import com.example.core.presentation.designsystem.components.ANIMATION_DELAY
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val gameRepository: GameRepository
): ViewModel() {

    var state by mutableStateOf(DetailsState())
        private set

    init {
        viewModelScope.launch {
            delay(ANIMATION_DELAY.toLong())
            state = state.copy(scrollEnabled = true)
        }
    }

    fun getGameById(gameId: Int) {
        viewModelScope.launch {
            gameRepository.getGameById(gameId).collectLatest {
                state = state.copy(game = it)
            }

        }
        viewModelScope.launch {
            val request = gameRepository.fetchGameDetails(gameId)

            if (request is Result.Success) {
                state.game?.let {
                    gameRepository.updateGameDetails(GameDetails(it.roomId, request.data.description))
                }
            }
        }
    }
}