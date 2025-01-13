package com.ondrnovy.nbaplayers.presentation.model

sealed class TeamDetailUiState {
    data object Empty : TeamDetailUiState()

    data object Loading : TeamDetailUiState()

    data class Content(
        val id: String,
        val conference: String,
        val division: String,
        val city: String,
        val name: String,
        val fullName: String,
        val abbreviation: String
    ) : TeamDetailUiState()

    data class Error(val message: String) : TeamDetailUiState()
}
