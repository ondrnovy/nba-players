package com.ondrnovy.nbaplayers.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ondrnovy.nbaplayers.R
import com.ondrnovy.nbaplayers.presentation.model.TeamDetailUiState
import com.ondrnovy.nbaplayers.presentation.theme.NBAPlayersTheme
import com.ondrnovy.nbaplayers.presentation.view.component.ErrorView
import com.ondrnovy.nbaplayers.presentation.view.component.InfoItem
import com.ondrnovy.nbaplayers.presentation.view.component.LoaderView
import com.ondrnovy.nbaplayers.presentation.view.component.ScaffoldWithTopBar
import com.ondrnovy.nbaplayers.presentation.viewmodel.TeamDetailViewModel

@Composable
fun TeamDetailScreen(
    navController: NavController,
    viewModel: TeamDetailViewModel,
) {
    val uiState by viewModel.teamDetailUiState.collectAsState()

    TeamDetailContent(
        uiState = uiState,
        onBackPressed = { navController.popBackStack() }
    )
}



@Composable
private fun TeamDetailContent(
    uiState: TeamDetailUiState,
    onBackPressed: () -> Unit = {},
) {
    ScaffoldWithTopBar(
        modifier = Modifier
            .fillMaxSize(),
        title = if(uiState is TeamDetailUiState.Content) uiState.fullName else "",
        onBackPressed = onBackPressed,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            when (uiState) {
                is TeamDetailUiState.Content -> {
                    Content(
                        uiState = uiState,
                    )
                }
                TeamDetailUiState.Empty -> {}
                is TeamDetailUiState.Error -> ErrorView(
                    modifier = Modifier.fillMaxSize(),
                    message = uiState.message,
                )
                TeamDetailUiState.Loading -> LoaderView(
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}


@Composable
private fun Content(
    uiState: TeamDetailUiState.Content,
) {
    Column (
        modifier = Modifier.fillMaxSize()
    ){
        InfoItem(stringResource(id = R.string.city), uiState.city)
        InfoItem(stringResource(id = R.string.name), uiState.name)
        InfoItem(stringResource(id = R.string.division), uiState.division)
        InfoItem(stringResource(id = R.string.abbreviation), uiState.abbreviation)
        InfoItem(stringResource(id = R.string.conference), uiState.conference)
    }
}

@Preview(showBackground = true)
@Composable
fun TeamDetailScreenPreview() {
    NBAPlayersTheme {
        TeamDetailContent(
            uiState = TeamDetailUiState.Content(
                id = "1",
                fullName = "Team Name",
                city = "City",
                name = "Name",
                division = "Division",
                abbreviation = "Abbreviation",
                conference = "Conference",
            )
        )
    }
}