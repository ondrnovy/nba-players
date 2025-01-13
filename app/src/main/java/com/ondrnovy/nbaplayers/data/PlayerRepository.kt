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
        cursor: Int?,
        perPage: Int,
    ) = try {
            Result.success(ballDontLieApi.getPlayers(cursor, perPage).toPaginatedPlayersEntity())
        } catch (e: Exception) {
            Result.failure(Exception("Network error: ${e.localizedMessage}", e))
        }

    suspend fun getPlayerById(
        id: Int,
    ) = try {
        val player = ballDontLieApi.getPlayerById(id).data
        Result.success(player.toEntity())
    } catch (e: Exception) {
        Result.failure(Exception("Network error: ${e.localizedMessage}", e))
    }
}