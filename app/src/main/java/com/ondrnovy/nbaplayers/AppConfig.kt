/*
* Urheberrechtshinweis: Diese Software ist urheberrechtlich geschützt. Das Urheberrecht liegt bei
* Research Industrial Systems Engineering (RISE) Forschungs-, Entwicklungs- und Großprojektberatung GmbH,
* soweit nicht im Folgenden näher gekennzeichnet.
*/
package com.ondrnovy.nbaplayers

// If there would be more build variants, these variables should be present in BuildConfig
object AppConfig {
    const val BASE_URL = "https://www.balldontlie.io/api/v1/"
    const val API_EMAIL = "ondrnovy@gmail.com"
    // Should be present in local.properties because of security, but I dont mind to share my API_KEY publically
    const val API_KEY = "fbb972e3-1b6c-4297-a594-9138eddf1e07"
}