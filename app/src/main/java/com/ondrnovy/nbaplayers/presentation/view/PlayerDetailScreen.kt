import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.ondrnovy.nbaplayers.presentation.theme.NBAPlayersTheme
import com.ondrnovy.nbaplayers.presentation.viewmodel.PlayersViewModel

@Composable
fun PlayerDetailScreen(
    navController: NavController,
    viewModel: PlayersViewModel,
) {
    //val uiState by viewModel.uiState.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

    }
}

@Preview(showBackground = true)
@Composable
fun PlayerDetailScreenPreview() {
    NBAPlayersTheme {
    }
}