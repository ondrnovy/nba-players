package com.ondrnovy.nbaplayers.presentation.model

import androidx.paging.PagingData

data class ListOfPlayersUiState(
    val pagedPlayers: PagingData<PlayerListItemUiState>?
)

data class PlayerListItemUiState(
    val id: String,
    val fullName: String,
    val position: String,
    val teamName: String,
)
