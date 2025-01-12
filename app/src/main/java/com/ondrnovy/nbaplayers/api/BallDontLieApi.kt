package com.ondrnovy.nbaplayers.api

import com.ondrnovy.nbaplayers.api.model.PaginatedResponse
import com.ondrnovy.nbaplayers.api.model.Player
import com.ondrnovy.nbaplayers.api.model.Team
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BallDontLieApi {
    @GET("players")
    fun getPlayers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<PaginatedResponse<Player>>

    @GET("players/{id}")
    fun getPlayerById(
        @Path("id") id: Int
    ): Call<Player>

    @GET("teams")
    fun getTeams(): Call<List<Team>>

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