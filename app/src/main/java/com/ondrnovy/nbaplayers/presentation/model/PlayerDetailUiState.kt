package com.ondrnovy.nbaplayers.presentation.model


sealed class PlayerDetailUiState{
    object Loading : PlayerDetailUiState()

    data class Content(
        val id: String,
        val fullName: String,
        val position: String,
        val height: String,
        val weight: String,
        val jerseyNumber: String,
        val college: String,
        val country: String,
        val draftYear: Int,
        val draftRound: Int,
        val draftNumber: Int,
        val teamName: String,
    ) : PlayerDetailUiState()

    data class Error(val message: String) : PlayerDetailUiState()
}
