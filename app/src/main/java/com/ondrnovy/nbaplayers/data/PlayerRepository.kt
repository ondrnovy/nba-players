/*
* Urheberrechtshinweis: Diese Software ist urheberrechtlich geschützt. Das Urheberrecht liegt bei
* Research Industrial Systems Engineering (RISE) Forschungs-, Entwicklungs- und Großprojektberatung GmbH,
* soweit nicht im Folgenden näher gekennzeichnet.
*/
package com.ondrnovy.nbaplayers.data

import com.ondrnovy.nbaplayers.api.BallDontLieApi

class PlayerRepository(
    private val ballDontLieApi: BallDontLieApi,
) {
    suspend fun getPlayers(
        page: Int,
        pageSize: Int,
    ) = try {
            Result.success(ballDontLieApi.getPlayers(page, pageSize).data.map { it.toEntity() })
        } catch (e: Exception) {
            Result.failure(Exception("Network error: ${e.localizedMessage}", e))
        }

}