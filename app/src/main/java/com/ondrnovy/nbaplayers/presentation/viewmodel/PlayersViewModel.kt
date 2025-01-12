/*
* Urheberrechtshinweis: Diese Software ist urheberrechtlich geschützt. Das Urheberrecht liegt bei
* Research Industrial Systems Engineering (RISE) Forschungs-, Entwicklungs- und Großprojektberatung GmbH,
* soweit nicht im Folgenden näher gekennzeichnet.
*/
package com.ondrnovy.nbaplayers.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ondrnovy.nbaplayers.data.PlayerRepository
import com.ondrnovy.nbaplayers.data.model.PlayerEntity
import com.ondrnovy.nbaplayers.presentation.view.ListOfPlayersUiState
import com.ondrnovy.nbaplayers.presentation.view.PlayerDetailUiState
import com.ondrnovy.nbaplayers.presentation.view.PlayerListItemUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class PlayersViewModel(
    val playerRepository: PlayerRepository,
) : ViewModel() {

    private val viewModelState =
        MutableStateFlow(
            PlayersViewModelState(
                isLoading = false,
                playerList = emptyList(),
            ),
        )

    internal val listOfPlayersUiState =
        viewModelState
            .map { it.toListOfPlayersUiState() }
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                viewModelState.value.toListOfPlayersUiState(),
            )

    internal val playerDetailUiState =
        viewModelState
            .map { it.toPlayerDetailUiState() }
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                viewModelState.value.toPlayerDetailUiState(),
            )






}

data class PlayersViewModelState(
    val isLoading: Boolean,
    val playerList: List<PlayerEntity>,
    val selectedPlayer: PlayerEntity? = null,
) {
    fun toListOfPlayersUiState(): ListOfPlayersUiState =
        ListOfPlayersUiState(
            isLoading = isLoading,
            playerList = playerList.map {
                PlayerListItemUiState(
                    name = it.firstName,
                )
            },
        )

    fun toPlayerDetailUiState(): PlayerDetailUiState? {
        selectedPlayer ?: return null

        return PlayerDetailUiState(
            name = selectedPlayer.firstName,
        )
    }
}