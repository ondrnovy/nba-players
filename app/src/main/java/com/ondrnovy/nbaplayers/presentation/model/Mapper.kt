/*
* Urheberrechtshinweis: Diese Software ist urheberrechtlich geschützt. Das Urheberrecht liegt bei
* Research Industrial Systems Engineering (RISE) Forschungs-, Entwicklungs- und Großprojektberatung GmbH,
* soweit nicht im Folgenden näher gekennzeichnet.
*/
package com.ondrnovy.nbaplayers.presentation.model

import com.ondrnovy.nbaplayers.data.model.PlayerEntity

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
        draftYear = draftYear,
        draftRound = draftRound,
        draftNumber = draftNumber,
        teamName = team.name
    )
}