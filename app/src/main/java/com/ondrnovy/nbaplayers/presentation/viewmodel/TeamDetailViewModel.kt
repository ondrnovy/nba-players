package com.ondrnovy.nbaplayers.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ondrnovy.nbaplayers.data.TeamRepository
import com.ondrnovy.nbaplayers.data.model.TeamEntity
import com.ondrnovy.nbaplayers.presentation.model.TeamDetailUiState
import com.ondrnovy.nbaplayers.presentation.model.toTeamDetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TeamDetailViewModel(
    private val id: String,
    private val teamRepository: TeamRepository,
) : ViewModel() {

    private val viewModelState =
        MutableStateFlow(
            TeamViewModelState(
                isLoading = false,
                error = "",
                team = null,
            ),
        )

    internal val teamDetailUiState =
        viewModelState
            .map { it.toTeamDetailUiState() }
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                viewModelState.value.toTeamDetailUiState(),
            )

    private fun loadTeamById() {
        viewModelState.update {
            it.copy(
                isLoading = true,
            )
        }

        viewModelScope.launch {
            teamRepository.getTeamById(id.toInt())
                .onSuccess {  team ->
                    viewModelState.update {
                        it.copy(
                            isLoading = false,
                            team = team,
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
        loadTeamById()
    }
}

data class TeamViewModelState(
    val isLoading: Boolean,
    val error: String,
    val team: TeamEntity?,
) {
    fun toTeamDetailUiState(): TeamDetailUiState {
        return if (isLoading){
            TeamDetailUiState.Loading
        } else if (error.isNotEmpty()){
            TeamDetailUiState.Error(error)
        } else team?.toTeamDetailUiState() ?: TeamDetailUiState.Empty
    }
}