package com.ondrnovy.nbaplayers

/**
 * An object containing app configuration variables.
 */
object AppConfig {
    // If there would be more build variants, these variables should be present in BuildConfig

    const val BASE_URL = "https://api.balldontlie.io/v1/"
    // Should be present in local.properties because of security, but I dont mind to share my API_KEY publically
    const val API_KEY = "fbb972e3-1b6c-4297-a594-9138eddf1e07"

    const val PLAYERS_PAGE_SIZE = 35
}