package com.ondrnovy.nbaplayers.presentation.model


sealed class PlayerDetailUiState{
    data object Empty : PlayerDetailUiState()

    data object Loading : PlayerDetailUiState()

    data class Content(
        val id: String,
        val fullName: String,
        val position: String,
        val height: String,
        val weight: String,
        val jerseyNumber: String,
        val college: String,
        val country: String,
        val draftYear: String,
        val draftRound: String,
        val draftNumber: String,
        val teamId: String,
        val teamName: String,
    ) : PlayerDetailUiState()

    data class Error(val message: String) : PlayerDetailUiState()
}
