/*
* Urheberrechtshinweis: Diese Software ist urheberrechtlich geschützt. Das Urheberrecht liegt bei
* Research Industrial Systems Engineering (RISE) Forschungs-, Entwicklungs- und Großprojektberatung GmbH,
* soweit nicht im Folgenden näher gekennzeichnet.
*/
package com.ondrnovy.nbaplayers.presentation.routing

import ListOfPlayersScreen
import PlayerDetailScreen
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

    NavHost(navController, startDestination = PlayersRoutes.ListOfPlayers) {
        composable(PlayersRoutes.ListOfPlayers.route) {
            ListOfPlayersScreen(
                navController = navController,
                viewModel = viewModel,
            )
        }
        composable(PlayersRoutes.PlayerDetail.route) {
            PlayerDetailScreen(
                navController = navController,
                viewModel = viewModel,
            )
        }
    }
}
