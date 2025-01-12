/*
* Urheberrechtshinweis: Diese Software ist urheberrechtlich geschützt. Das Urheberrecht liegt bei
* Research Industrial Systems Engineering (RISE) Forschungs-, Entwicklungs- und Großprojektberatung GmbH,
* soweit nicht im Folgenden näher gekennzeichnet.
*/
package com.ondrnovy.nbaplayers.presentation.routing

sealed class Routes(val route: String) {
    data object ListOfPlayers : Routes("list_of_players")
    data object PlayerDetail : Routes("player_detail")
    data object TeamDetail : Routes("team_detail")
}