/*
* Urheberrechtshinweis: Diese Software ist urheberrechtlich geschützt. Das Urheberrecht liegt bei
* Research Industrial Systems Engineering (RISE) Forschungs-, Entwicklungs- und Großprojektberatung GmbH,
* soweit nicht im Folgenden näher gekennzeichnet.
*/
package com.ondrnovy.nbaplayers.presentation.routing

import ListOfPlayersScreen
import PlayerDetailScreen
import TeamDetailScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ondrnovy.nbaplayers.presentation.viewmodel.PlayersViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlayersRouter() {
    val navController = rememberNavController()
    val viewModel: PlayersViewModel = koinViewModel()

    NavHost(navController, startDestination = Routes.ListOfPlayers.route) {
        composable(Routes.ListOfPlayers.route) {
            ListOfPlayersScreen(
                navController = navController,
                viewModel = viewModel,
            )
        }
        composable(Routes.PlayerDetail.route) {
            PlayerDetailScreen(
                navController = navController,
                viewModel = viewModel,
            )
        }
        composable(Routes.TeamDetail.route) {
            TeamDetailScreen(
                navController = navController,
                viewModel = koinViewModel(),
            )
        }
    }
}