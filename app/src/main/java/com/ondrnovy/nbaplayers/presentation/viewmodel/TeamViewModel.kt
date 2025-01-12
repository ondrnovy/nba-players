/*
* Urheberrechtshinweis: Diese Software ist urheberrechtlich geschützt. Das Urheberrecht liegt bei
* Research Industrial Systems Engineering (RISE) Forschungs-, Entwicklungs- und Großprojektberatung GmbH,
* soweit nicht im Folgenden näher gekennzeichnet.
*/
package com.ondrnovy.nbaplayers.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ondrnovy.nbaplayers.data.TeamRepository
import com.ondrnovy.nbaplayers.data.model.TeamEntity
import com.ondrnovy.nbaplayers.presentation.view.TeamDetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TeamViewModel(
    val teamRepository: TeamRepository,
) : ViewModel() {

    private val viewModelState =
        MutableStateFlow(
            TeamViewModelState(
                isLoading = false,
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
}

data class TeamViewModelState(
    val isLoading: Boolean,
    val team: TeamEntity?,
) {
    fun toTeamDetailUiState(): TeamDetailUiState {
        return TeamDetailUiState(
            isLoading = isLoading,
            name = team?.name ?: "",
        )
    }
}