package com.ondrnovy.nbaplayers.data.model

data class TeamEntity(
    val id: Int,
    val conference: String,
    val division: String,
    val city: String,
    val name: String,
    val fullName: String,
    val abbreviation: String
)