package com.ondrnovy.nbaplayers.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ondrnovy.nbaplayers.api.BallDontLieApi
import com.ondrnovy.nbaplayers.network.AuthenticationInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun provideOkHttpClient(
    authToken: String,
): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(
            AuthenticationInterceptor(authToken = authToken)
        )
        .build()
}

/**
 * Creates new Retrofit instance
 *
 * @param baseUrl API base URL
 *
 * @return Retrofit instance
 */
fun provideRetrofit(
    baseUrl: String,
    okHttpClient: OkHttpClient,
): Retrofit {
    val gson: Gson = GsonBuilder()
        .setFieldNamingPolicy(com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}


/**
 * Creates an implementation of the API endpoints defined by the service interface.
 *
 * @param retrofit Retrofit instance
 *
 * @return Service instance
 *
 * @see Retrofit.create
 */
fun provideApiService(retrofit: Retrofit): BallDontLieApi = retrofit.create(BallDontLieApi::class.java)