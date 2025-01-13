import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
import com.ondrnovy.nbaplayers.presentation.view.component.ScaffoldWithTopBar
import com.ondrnovy.nbaplayers.presentation.viewmodel.TeamDetailViewModel

@Composable
fun TeamDetailScreen(
    navController: NavController,
    viewModel: TeamDetailViewModel,
) {
    val uiState by viewModel.teamDetailUiState.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

    }
}



@Composable
private fun TeamDetailContent(
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

        }
    }
}

@Preview(showBackground = true)
@Composable
fun TeamDetailScreenPreview() {
    NBAPlayersTheme {
    }
}