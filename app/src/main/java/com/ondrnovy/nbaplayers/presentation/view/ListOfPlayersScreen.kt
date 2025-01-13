package com.ondrnovy.nbaplayers.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ondrnovy.nbaplayers.presentation.model.PlayerListItemUiState
import com.ondrnovy.nbaplayers.presentation.routing.Route
import com.ondrnovy.nbaplayers.presentation.view.component.ErrorView
import com.ondrnovy.nbaplayers.presentation.view.component.LoaderView
import com.ondrnovy.nbaplayers.presentation.view.component.ScaffoldWithTopBar
import com.ondrnovy.nbaplayers.presentation.viewmodel.ListOfPlayersViewModel

/**
 * Screen with a list of players
 *
 * @param navController Navigation controller
 * @param viewModel ViewModel for this screen
 */
@Composable
fun ListOfPlayersScreen(
    navController: NavController,
    viewModel: ListOfPlayersViewModel,
) {
    val lazyPagingItems = viewModel.playersPagingDataFlow.collectAsLazyPagingItems()


    ListOfPlayersContent(
        lazyPagingItems = lazyPagingItems,
        navigateToPlayerDetail = {
            navController.navigate(Route.PlayerDetail.route.replace("{${Route.PLAYER_DETAIL_ID_KEY}}", it))
        },
    )
}

@Composable
fun PlayerListItem(
    uiState: PlayerListItemUiState,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    ListItem(
        modifier = modifier.clickable { onClick() },
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
private fun ListOfPlayersContent(
    lazyPagingItems: LazyPagingItems<PlayerListItemUiState>,
    navigateToPlayerDetail: (id: String) -> Unit,
) {
    ScaffoldWithTopBar(
        modifier = Modifier
            .fillMaxSize(),
        showBackButton = false,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            when (lazyPagingItems.loadState.refresh) {
                is LoadState.Loading -> {
                    LoaderView(
                        modifier = Modifier.fillMaxSize()
                    )
                }

                is LoadState.Error -> {
                    ErrorView(
                        modifier = Modifier.fillMaxWidth(),
                        message = (lazyPagingItems.loadState.refresh as LoadState.Error).error.message ?: "Unknown error",
                    )
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
                                    onClick = {
                                        navigateToPlayerDetail(item.id)
                                    }
                                )
                            }
                        }
                    }

                    when (lazyPagingItems.loadState.append) {
                        is LoadState.Loading -> {
                            LoaderView(
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        is LoadState.Error -> {
                            ErrorView(
                                modifier = Modifier.fillMaxWidth(),
                                message = (lazyPagingItems.loadState.append as LoadState.Error).error.message ?: "Unknown error",
                            )
                        }

                        else -> {}
                    }
                }
            }
        }
    }
}