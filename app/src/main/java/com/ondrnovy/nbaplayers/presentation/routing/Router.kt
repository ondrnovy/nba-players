package com.ondrnovy.nbaplayers.presentation.routing

import com.ondrnovy.nbaplayers.presentation.view.ListOfPlayersScreen
import com.ondrnovy.nbaplayers.presentation.view.PlayerDetailScreen
import com.ondrnovy.nbaplayers.presentation.view.TeamDetailScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ondrnovy.nbaplayers.presentation.routing.Route.Companion.PLAYER_DETAIL_ID_KEY
import com.ondrnovy.nbaplayers.presentation.routing.Route.Companion.TEAM_DETAIL_ID_KEY
import com.ondrnovy.nbaplayers.presentation.viewmodel.PlayerDetailViewModel
import com.ondrnovy.nbaplayers.presentation.viewmodel.TeamDetailViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun Router() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Route.ListOfPlayers.route) {
        composable(Route.ListOfPlayers.route) {
            ListOfPlayersScreen(
                navController = navController,
                viewModel = koinViewModel(),
            )
        }
        composable(Route.PlayerDetail.route) { navBackStackEntry ->
            val playerId = navBackStackEntry.arguments?.getString(PLAYER_DETAIL_ID_KEY)

            PlayerDetailScreen(
                navController = navController,
                viewModel = koinViewModel<PlayerDetailViewModel>(
                    parameters = { parametersOf(playerId) }
                ),
            )
        }
        composable(Route.TeamDetail.route) { navBackStackEntry ->
            val teamId = navBackStackEntry.arguments?.getString(TEAM_DETAIL_ID_KEY)

            TeamDetailScreen(
                navController = navController,
                viewModel = koinViewModel<TeamDetailViewModel>(
                    parameters = { parametersOf(teamId) }
                ),
            )
        }
    }
}