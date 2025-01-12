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
    fun getTeams(
        onSuccess: (List<TeamEntity>) -> Unit,
        onError: (String) -> Unit
    ) {
        val call = ballDontLieApi.getTeams()
        call.enqueue(object : Callback<List<TeamApiObject>> {
            override fun onResponse(
                call: Call<List<TeamApiObject>>,
                response: Response<List<TeamApiObject>>
            ) {
                if (response.isSuccessful) {
                    val teams = response.body() ?: emptyList()
                    onSuccess(teams.map { it.toEntity() })
                } else {
                    onError("Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<TeamApiObject>>, t: Throwable) {
                onError("Network error: ${t.localizedMessage}")
            }
        })
    }
}