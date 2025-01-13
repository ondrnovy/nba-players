package com.ondrnovy.nbaplayers.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ondrnovy.nbaplayers.data.PlayerRepository
import com.ondrnovy.nbaplayers.data.model.PlayerEntity
import com.ondrnovy.nbaplayers.presentation.model.PlayerDetailUiState
import com.ondrnovy.nbaplayers.presentation.model.toPlayerDetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlayerDetailViewModel(
    private val id: String,
    private val playerRepository: PlayerRepository,
) : ViewModel() {

    private val viewModelState =
        MutableStateFlow(
            PlayerDetailViewModelState(
                isLoading = false,
                error = "",
                selectedPlayer = null,
            ),
        )

    internal val playerDetailUiState =
        viewModelState
            .map { it.toPlayerDetailUiState() }
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                viewModelState.value.toPlayerDetailUiState(),
            )


    private fun loadPlayerById() {
        viewModelState.update {
            it.copy(
                isLoading = true,
            )
        }

        viewModelScope.launch {
            playerRepository.getPlayerById(id.toInt())
                .onSuccess {  player ->
                    viewModelState.update {
                        it.copy(
                            isLoading = false,
                            selectedPlayer = player,
                        )
                    }
                }
                .onFailure { exception ->
                    viewModelState.update {
                        it.copy(
                            isLoading = false,
                            error = exception.message ?: "Unknown error",
                        )
                    }
                }
        }
    }

    init {
        loadPlayerById()
    }
}

data class PlayerDetailViewModelState(
    val isLoading: Boolean,
    val error: String,
    val selectedPlayer: PlayerEntity?,
) {
    fun toPlayerDetailUiState(): PlayerDetailUiState {
        return if (isLoading){
            PlayerDetailUiState.Loading
        } else if (error.isNotEmpty()){
            PlayerDetailUiState.Error(error)
        } else selectedPlayer?.toPlayerDetailUiState() ?: PlayerDetailUiState.Empty
    }
}