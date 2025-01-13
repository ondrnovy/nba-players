import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.ondrnovy.nbaplayers.presentation.theme.NBAPlayersTheme
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

@Preview(showBackground = true)
@Composable
fun TeamDetailScreenPreview() {
    NBAPlayersTheme {
    }
}