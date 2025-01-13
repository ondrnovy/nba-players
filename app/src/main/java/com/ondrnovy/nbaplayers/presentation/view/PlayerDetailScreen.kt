import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.ondrnovy.nbaplayers.presentation.theme.NBAPlayersTheme
import com.ondrnovy.nbaplayers.presentation.view.component.ErrorView
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
        }
    )
}


@Composable
private fun PlayerDetailContent(
    uiState: PlayerDetailUiState,
    onBackPressed: () -> Unit = {},
) {
    ScaffoldWithTopBar(
        modifier = Modifier
            .fillMaxSize(),
        title = if(uiState is PlayerDetailUiState.Content) uiState.fullName else stringResource(id = R.string.app_name),
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
    uiState: PlayerDetailUiState.Content
) {
    Column (
        modifier = Modifier.fillMaxSize()
    ){
        Text(text = uiState.fullName)
        Text(text = uiState.position)
        Text(text = uiState.teamName)
        Text(text = uiState.height)
        Text(text = uiState.weight)
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
                height = "Height",
                weight = "55 kg",
                jerseyNumber = "123",
                college = "Harvard",
                country = "Country",
                draftYear = 2022,
                draftRound = 5,
                draftNumber = 4,
            )
        )
    }
}