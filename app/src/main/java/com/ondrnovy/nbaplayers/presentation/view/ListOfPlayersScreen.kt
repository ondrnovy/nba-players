import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ondrnovy.nbaplayers.presentation.model.PlayerListItemUiState
import com.ondrnovy.nbaplayers.presentation.view.CenteredLoader
import com.ondrnovy.nbaplayers.presentation.view.ScaffoldWithTopBar
import com.ondrnovy.nbaplayers.presentation.viewmodel.PlayersViewModel


@Composable
fun ListOfPlayersScreen(
    navController: NavController,
    viewModel: PlayersViewModel,
) {
    val lazyPagingItems = viewModel.playersPagingDataFlow.collectAsLazyPagingItems()


    ListOfPlayersContent(
        lazyPagingItems = lazyPagingItems,
    )
}


@Composable
fun PlayerListItem(
    uiState: PlayerListItemUiState,
    modifier: Modifier = Modifier,
) {
    ListItem(
        modifier = modifier,
        headlineContent = {
            Text(text = uiState.fullName)
        },
        supportingContent = {
            Text(text = uiState.position)
        },
        trailingContent = {
            Text(text = uiState.teamName)
        }
    )
}

@Composable
fun ListOfPlayersContent(
    lazyPagingItems: LazyPagingItems<PlayerListItemUiState>,
) {

    ScaffoldWithTopBar(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            when (lazyPagingItems.loadState.refresh) {
                is LoadState.Loading -> {
                    CenteredLoader()
                }

                is LoadState.Error -> {
                    Text("An error occurred")
                }

                else -> {
                    LazyColumn {
                        items(lazyPagingItems.itemCount) { index ->
                            val item = lazyPagingItems[index]
                            item?.let {
                                if (index > 0) {
                                    HorizontalDivider(modifier = Modifier.fillMaxWidth())
                                }

                                PlayerListItem(
                                    modifier = Modifier
                                        .padding(vertical = 8.dp)
                                        .fillMaxWidth(),
                                    uiState = it,
                                )
                            }
                        }
                    }

                    when (lazyPagingItems.loadState.append) {
                        is LoadState.Loading -> {
                            CenteredLoader()
                        }

                        is LoadState.Error -> {
                            Text("An error occurred")
                        }

                        else -> {}
                    }
                }
            }
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun ListOfPlayersScreenPreview() {
    NBAPlayersTheme {
        ListOfPlayersContent(
            uiState = ListOfPlayersUiState.Content(
                playerList = listOf(
                    PlayerListItemUiState("1","Player 1"),
                    PlayerListItemUiState("2","Player 2"),
                    PlayerListItemUiState("3","Player 3"),
                    PlayerListItemUiState("4","Player 4"),
                )
            )
        )
    }
}*/