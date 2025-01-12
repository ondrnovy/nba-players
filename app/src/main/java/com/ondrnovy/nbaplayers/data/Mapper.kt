/*
* Urheberrechtshinweis: Diese Software ist urheberrechtlich geschützt. Das Urheberrecht liegt bei
* Research Industrial Systems Engineering (RISE) Forschungs-, Entwicklungs- und Großprojektberatung GmbH,
* soweit nicht im Folgenden näher gekennzeichnet.
*/
package com.ondrnovy.nbaplayers.data

import com.ondrnovy.nbaplayers.api.model.PlayerApiObject
import com.ondrnovy.nbaplayers.api.model.TeamApiObject
import com.ondrnovy.nbaplayers.data.model.PlayerEntity
import com.ondrnovy.nbaplayers.data.model.TeamEntity

fun PlayerApiObject.toEntity() = PlayerEntity(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    position = this.position,
    height = this.height,
    weight = this.weight,
    jerseyNumber = this.jerseyNumber,
    college = this.college,
    country = this.country,
    draftYear = this.draftYear,
    draftRound = this.draftRound,
    draftNumber = this.draftNumber,
    team = this.team
)


fun TeamApiObject.toEntity() = TeamEntity(
    id = this.id,
    conference = this.conference,
    division = this.division,
    city = this.city,
    name = this.name,
    fullName = this.fullName,
    abbreviation = this.abbreviation
)
