/*
* Urheberrechtshinweis: Diese Software ist urheberrechtlich geschützt. Das Urheberrecht liegt bei
* Research Industrial Systems Engineering (RISE) Forschungs-, Entwicklungs- und Großprojektberatung GmbH,
* soweit nicht im Folgenden näher gekennzeichnet.
*/
package com.ondrnovy.nbaplayers.data

import com.ondrnovy.nbaplayers.api.BallDontLieApi
import com.ondrnovy.nbaplayers.api.model.PaginatedResponse
import com.ondrnovy.nbaplayers.api.model.PlayerApiObject
import com.ondrnovy.nbaplayers.data.model.PlayerEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayerRepository(
    private val ballDontLieApi: BallDontLieApi,
) {
    fun getPlayers(
        page: Int,
        perPage: Int,
        onSuccess: (List<PlayerEntity>) -> Unit,
        onError: (String) -> Unit
    ) {
        val call = ballDontLieApi.getPlayers(page, perPage)
        call.enqueue(object : Callback<PaginatedResponse<PlayerApiObject>> {
            override fun onResponse(
                call: Call<PaginatedResponse<PlayerApiObject>>,
                response: Response<PaginatedResponse<PlayerApiObject>>
            ) {
                if (response.isSuccessful) {
                    val players = response.body()?.data ?: emptyList()
                    onSuccess(players.map { it.toEntity() })
                } else {
                    onError("Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<PaginatedResponse<PlayerApiObject>>, t: Throwable) {
                onError("Network error: ${t.localizedMessage}")
            }
        })
    }
}