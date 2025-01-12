import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.ondrnovy.nbaplayers.presentation.theme.NBAPlayersTheme
import com.ondrnovy.nbaplayers.presentation.model.ListOfPlayersUiState
import com.ondrnovy.nbaplayers.presentation.model.PlayerListItemUiState
import com.ondrnovy.nbaplayers.presentation.model.toUiState
import com.ondrnovy.nbaplayers.presentation.viewmodel.PlayersViewModel


@Composable
fun ListOfPlayersScreen(
    navController: NavController,
    viewModel: PlayersViewModel,
) {
    val uiState by viewModel.listOfPlayersUiState.collectAsState()

    val lazyPagingItems = viewModel.pagingDataFlow.collectAsLazyPagingItems()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {


            Text(text = "Players")

            LazyColumn {
                items(lazyPagingItems.itemCount) { index ->
                    val item = lazyPagingItems[index]
                    item?.toUiState()?.let {
                        PlayerListItem(it)
                    }
                }
            }

            when (lazyPagingItems.loadState.refresh) {
                is LoadState.Loading -> {
                    CircularProgressIndicator()
                }

                is LoadState.Error -> {
                    Text("An error occurred")
                }

                else -> {

                }
            }

            Button(onClick = {
                lazyPagingItems.refresh()
            }) {
                Text(text = "Load more")
            }
        }
    }

    /*LazyColumn {
        items(lazyPagingItems.itemCount) { item ->
            item.let {
                Text(text = it.toString()) // Customize based on your data
            }
        }

        when {
            lazyPagingItems.loadState.append is LoadState.Loading -> {
                item { CircularProgressIndicator() } // Loading more data
            }
            lazyPagingItems.loadState.refresh is LoadState.Loading -> {
                // Show full-screen loading indicator during the initial load
                item { CircularProgressIndicator(modifier = Modifier.fillMaxSize()) }
            }
            lazyPagingItems.loadState.append is LoadState.Error -> {
                val e = (lazyPagingItems.loadState.append as LoadState.Error).error
                item {
                    Text("Error: ${e.localizedMessage}")
                }
            }
        }
    }*/

    /*ListOfPlayersContent(
        uiState = uiState,
    )*/
}


@Composable
fun PlayerListItem(
    uiState: PlayerListItemUiState
) {
    ListItem(
        headlineContent = {
            Text(text = uiState.name)
        }
    )
}

@Composable
fun ListOfPlayersContent(
    uiState: ListOfPlayersUiState,
) {

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column (modifier = Modifier.padding(innerPadding)){

            when (uiState) {
                is ListOfPlayersUiState.Content -> {

                    /*uiState.pagedPlayers.

                    LazyColumn {
                        items(
                            uiState.playerList.size,
                            key = { it.id }
                        ) { index ->
                            val message = lazyPagingItems[index]
                            if (message != null) {
                                MessageRow(message)
                            } else {
                                MessagePlaceholder()
                            }
                        }
                    }*/
                }
                is ListOfPlayersUiState.Error -> {}
                ListOfPlayersUiState.Loading -> {}
            }
        }
    }
}

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
}