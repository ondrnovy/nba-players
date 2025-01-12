package com.ondrnovy.nbaplayers.data.model

import com.ondrnovy.nbaplayers.api.model.TeamApiObject

data class PlayerEntity(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val position: String,
    val height: String,
    val weight: String,
    val jerseyNumber: String,
    val college: String,
    val country: String,
    val draftYear: Int,
    val draftRound: Int,
    val draftNumber: Int,
    val team: TeamApiObject
)
