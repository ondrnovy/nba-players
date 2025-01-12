/*
* Urheberrechtshinweis: Diese Software ist urheberrechtlich geschützt. Das Urheberrecht liegt bei
* Research Industrial Systems Engineering (RISE) Forschungs-, Entwicklungs- und Großprojektberatung GmbH,
* soweit nicht im Folgenden näher gekennzeichnet.
*/
package com.ondrnovy.nbaplayers.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class AuthenticationInterceptor(private val authToken: String) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val requestWithAuth: Request = originalRequest.newBuilder()
            .header("Authorization", "$authToken")
            .build()
        return chain.proceed(requestWithAuth)
    }
}