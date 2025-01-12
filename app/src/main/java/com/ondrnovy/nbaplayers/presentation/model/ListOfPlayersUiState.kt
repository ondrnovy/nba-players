package com.ondrnovy.nbaplayers.presentation.model

sealed class ListOfPlayersUiState {
    data class Content(
        //val pagedPlayers: PagingData<PlayerListItemUiState>,
        val playerList: List<PlayerListItemUiState>,
        //val isLoadingMore: Boolean,
    ): ListOfPlayersUiState()

    data object Loading: ListOfPlayersUiState()

    data class Error(
        val message: String,
    ): ListOfPlayersUiState()
}

data class PlayerListItemUiState(
    val id: String,
    val name: String,
)
