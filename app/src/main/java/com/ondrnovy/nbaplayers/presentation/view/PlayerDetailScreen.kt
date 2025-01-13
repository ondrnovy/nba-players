package com.ondrnovy.nbaplayers.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ondrnovy.nbaplayers.R
import com.ondrnovy.nbaplayers.presentation.model.PlayerDetailUiState
import com.ondrnovy.nbaplayers.presentation.routing.Route
import com.ondrnovy.nbaplayers.presentation.theme.NBAPlayersTheme
import com.ondrnovy.nbaplayers.presentation.view.component.ErrorView
import com.ondrnovy.nbaplayers.presentation.view.component.InfoItem
import com.ondrnovy.nbaplayers.presentation.view.component.LoaderView
import com.ondrnovy.nbaplayers.presentation.view.component.ScaffoldWithTopBar
import com.ondrnovy.nbaplayers.presentation.viewmodel.PlayerDetailViewModel

@Composable
fun PlayerDetailScreen(
    navController: NavController,
    viewModel: PlayerDetailViewModel,
) {
    val uiState by viewModel.playerDetailUiState.collectAsState()

    PlayerDetailContent(
        uiState = uiState,
        onBackPressed = {
            navController.popBackStack()
        },
        onShowTeamDetails = { teamId ->
            navController.navigate(Route.TeamDetail.route.replace("{${Route.TEAM_DETAIL_ID_KEY}}", teamId))
        },
    )
}


@Composable
private fun PlayerDetailContent(
    uiState: PlayerDetailUiState,
    onBackPressed: () -> Unit = {},
    onShowTeamDetails: (teamId: String) -> Unit = {},
) {
    ScaffoldWithTopBar(
        modifier = Modifier
            .fillMaxSize(),
        title = if(uiState is PlayerDetailUiState.Content) uiState.fullName else "",
        onBackPressed = onBackPressed,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            when (uiState) {
                is PlayerDetailUiState.Content -> {
                    Content(
                        uiState = uiState,
                        onShowTeamDetails = onShowTeamDetails,
                    )
                }
                PlayerDetailUiState.Empty -> {}
                is PlayerDetailUiState.Error -> ErrorView(
                    modifier = Modifier.fillMaxSize(),
                    message = uiState.message,
                )
                PlayerDetailUiState.Loading -> LoaderView(
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}

@Composable
private fun Content(
    uiState: PlayerDetailUiState.Content,
    onShowTeamDetails: (teamId: String) -> Unit = {},
) {
    Column (
        modifier = Modifier.fillMaxSize()
    ){
        InfoItem(stringResource(id = R.string.position), uiState.position)
        InfoItem(stringResource(id = R.string.height), uiState.height)
        InfoItem(stringResource(id = R.string.weight), uiState.weight)
        InfoItem(stringResource(id = R.string.college), uiState.college)
        InfoItem(stringResource(id = R.string.country), uiState.country)
        InfoItem(stringResource(id = R.string.jersey_number), uiState.jerseyNumber)
        InfoItem(stringResource(id = R.string.draft_number), uiState.draftNumber)
        InfoItem(stringResource(id = R.string.draft_year), uiState.draftYear)
        InfoItem(stringResource(id = R.string.draft_round), uiState.draftRound)
        InfoItem(stringResource(id = R.string.team_name), uiState.teamName)

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ){
            Button(
                onClick = {
                    onShowTeamDetails(uiState.teamId)
                }
            ) {
                Text(text = stringResource(id = R.string.moreAboutTeam))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PlayerDetailScreenPreview() {
    NBAPlayersTheme {
        PlayerDetailContent(
            uiState = PlayerDetailUiState.Content(
                id = "1",
                fullName = "Player Name",
                position = "Position",
                teamName = "Team Name",
                teamId = "1",
                height = "Height",
                weight = "55 kg",
                jerseyNumber = "123",
                college = "Harvard",
                country = "Country",
                draftYear = "2022",
                draftRound = "5",
                draftNumber = "4",
            )
        )
    }
}