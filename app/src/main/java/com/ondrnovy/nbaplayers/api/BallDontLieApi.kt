package com.ondrnovy.nbaplayers.api

import com.ondrnovy.nbaplayers.api.model.PaginatedResponse
import com.ondrnovy.nbaplayers.api.model.PlayerApiObject
import com.ondrnovy.nbaplayers.api.model.Response
import com.ondrnovy.nbaplayers.api.model.TeamApiObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BallDontLieApi {
    @GET("players")
    suspend fun getPlayers(
        @Query("cursor") cursor: Int?,
        @Query("per_page") perPage: Int
    ): PaginatedResponse<PlayerApiObject>

    @GET("players/{id}")
    suspend fun getPlayerById(
        @Path("id") id: Int
    ): Response<PlayerApiObject>

    @GET("teams")
    fun getTeams(): Call<List<TeamApiObject>>

    /*@GET("games")
    fun getGames(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<PaginatedResponse<Game>>

    @GET("stats")
    fun getStats(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<PaginatedResponse<Stat>>*/
}