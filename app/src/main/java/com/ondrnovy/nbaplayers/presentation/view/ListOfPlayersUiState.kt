package com.ondrnovy.nbaplayers.presentation.view

data class ListOfPlayersUiState(
    val isLoading: Boolean,
    val playerList: List<PlayerListItemUiState>,
)

data class PlayerListItemUiState(
    val name: String,
)
