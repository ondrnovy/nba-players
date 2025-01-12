/*
* Urheberrechtshinweis: Diese Software ist urheberrechtlich geschützt. Das Urheberrecht liegt bei
* Research Industrial Systems Engineering (RISE) Forschungs-, Entwicklungs- und Großprojektberatung GmbH,
* soweit nicht im Folgenden näher gekennzeichnet.
*/
package com.ondrnovy.nbaplayers.presentation.routing

sealed class PlayersRoutes(val route: String) {
    data object ListOfPlayers : PlayersRoutes("list_of_players")
    data object PlayerDetail : PlayersRoutes("player_detail")
}