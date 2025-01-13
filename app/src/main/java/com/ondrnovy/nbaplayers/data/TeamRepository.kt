/*
* Urheberrechtshinweis: Diese Software ist urheberrechtlich geschützt. Das Urheberrecht liegt bei
* Research Industrial Systems Engineering (RISE) Forschungs-, Entwicklungs- und Großprojektberatung GmbH,
* soweit nicht im Folgenden näher gekennzeichnet.
*/
package com.ondrnovy.nbaplayers.data

import com.ondrnovy.nbaplayers.api.BallDontLieApi
import com.ondrnovy.nbaplayers.api.model.PaginatedResponse
import com.ondrnovy.nbaplayers.api.model.PlayerApiObject
import com.ondrnovy.nbaplayers.api.model.TeamApiObject
import com.ondrnovy.nbaplayers.data.model.PlayerEntity
import com.ondrnovy.nbaplayers.data.model.TeamEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamRepository(
    private val ballDontLieApi: BallDontLieApi,
) {
    suspend fun getTeamById(
        id: Int,
    ) = try {
        val team = ballDontLieApi.getTeamById(id).data
        Result.success(team.toEntity())
    } catch (e: Exception) {
        Result.failure(Exception("Network error: ${e.localizedMessage}", e))
    }
}