/*
* Urheberrechtshinweis: Diese Software ist urheberrechtlich geschützt. Das Urheberrecht liegt bei
* Research Industrial Systems Engineering (RISE) Forschungs-, Entwicklungs- und Großprojektberatung GmbH,
* soweit nicht im Folgenden näher gekennzeichnet.
*/
package com.ondrnovy.nbaplayers.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.ondrnovy.nbaplayers.AppConfig.PLAYERS_PAGE_SIZE
import com.ondrnovy.nbaplayers.data.PlayerRepository
import com.ondrnovy.nbaplayers.data.model.PlayerEntity
import com.ondrnovy.nbaplayers.presentation.pagination.PlayersPagingSource
import com.ondrnovy.nbaplayers.presentation.model.ListOfPlayersUiState
import com.ondrnovy.nbaplayers.presentation.model.PlayerDetailUiState
import com.ondrnovy.nbaplayers.presentation.model.PlayerListItemUiState
import com.ondrnovy.nbaplayers.presentation.model.toUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class PlayersViewModel(
    val playerRepository: PlayerRepository,
) : ViewModel() {

    private val viewModelState =
        MutableStateFlow(
            PlayersViewModelState(
                isLoading = false,
                playerList = emptyList(),
                error = "",
            ),
        )

    private val pager = Pager(
        config = PagingConfig(pageSize = PLAYERS_PAGE_SIZE),
        pagingSourceFactory = {
            PlayersPagingSource(playerRepository = playerRepository)
        }
    )
    val playersPagingDataFlow = pager.flow.cachedIn(viewModelScope).map {
        it.map { it.toUiState() }
    }




    internal val playerDetailUiState =
        viewModelState
            .map { it.toPlayerDetailUiState() }
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                viewModelState.value.toPlayerDetailUiState(),
            )


    fun loadPlayers() {
        viewModelState.update {
            it.copy(
                isLoading = true,
            )
        }
    }

    init {

    }
}

data class PlayersViewModelState(
    val isLoading: Boolean,
    val error: String,
    val playerList: List<PlayerEntity>,
    val selectedPlayer: PlayerEntity? = null,
) {
    /*fun toListOfPlayersUiState(): ListOfPlayersUiState {
        return if (isLoading) {
            ListOfPlayersUiState.Loading
        }
        else if (error.isNotEmpty()) {
            ListOfPlayersUiState.Error(
                message = error,
            )
        }
        else {
            ListOfPlayersUiState.Content(
                playerList = playerList.map {
                    PlayerListItemUiState(
                        id = it.id.toString(),
                        name = it.firstName,
                    )
                },
            )
        }
    }*/

    fun toPlayerDetailUiState(): PlayerDetailUiState? {
        selectedPlayer ?: return null

        return PlayerDetailUiState(
            name = selectedPlayer.firstName,
        )
    }
}