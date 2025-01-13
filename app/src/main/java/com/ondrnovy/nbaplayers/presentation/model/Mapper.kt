package com.ondrnovy.nbaplayers.presentation.model

import com.ondrnovy.nbaplayers.data.model.PlayerEntity
import com.ondrnovy.nbaplayers.data.model.TeamEntity

fun PlayerEntity.toUiState() = PlayerListItemUiState(
    id = id.toString(),
    fullName = "$firstName $lastName",
    position = position,
    teamName = team.name,
)

fun PlayerEntity.toPlayerDetailUiState(): PlayerDetailUiState.Content {
    return PlayerDetailUiState.Content(
        id = id.toString(),
        fullName = "$firstName $lastName",
        position = position,
        height = height,
        weight = weight,
        jerseyNumber = jerseyNumber,
        college = college,
        country = country,
        draftYear = draftYear.toString(),
        draftRound = draftRound.toString(),
        draftNumber = draftNumber.toString(),
        teamId = team.id.toString(),
        teamName = team.name
    )
}

fun TeamEntity.toTeamDetailUiState(): TeamDetailUiState.Content {
    return TeamDetailUiState.Content(
        id = id.toString(),
        conference = conference,
        division = division,
        city = city,
        name = name,
        fullName = fullName,
        abbreviation = abbreviation,
    )
}