package com.ondrnovy.nbaplayers.presentation.routing

sealed class Route(val route: String) {
    data object ListOfPlayers : Route("list_of_players")
    data object PlayerDetail : Route("player_detail/{$PLAYER_DETAIL_ID_KEY}")
    data object TeamDetail : Route("team_detail/{$TEAM_DETAIL_ID_KEY}")

    companion object {
        const val PLAYER_DETAIL_ID_KEY = "id"
        const val TEAM_DETAIL_ID_KEY = "id"
    }
}