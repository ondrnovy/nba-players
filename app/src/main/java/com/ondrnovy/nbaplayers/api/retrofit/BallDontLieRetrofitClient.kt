package com.ondrnovy.nbaplayers.api.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BallDontLieRetrofitClient {
    private const val BASE_URL = "https://www.balldontlie.io/api/v1/"
    private const val API_EMAIL = "ondrnovy@gmail.com"
    // Should be present in local.properties because of security, but I dont mind to share my API_KEY publically
    private const val API_KEY = "fbb972e3-1b6c-4297-a594-9138eddf1e07"

    private val gson: Gson = GsonBuilder()
        .setFieldNamingPolicy(com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}