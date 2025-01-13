/*
* Urheberrechtshinweis: Diese Software ist urheberrechtlich geschützt. Das Urheberrecht liegt bei
* Research Industrial Systems Engineering (RISE) Forschungs-, Entwicklungs- und Großprojektberatung GmbH,
* soweit nicht im Folgenden näher gekennzeichnet.
*/
package com.ondrnovy.nbaplayers.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.ondrnovy.nbaplayers.AppConfig.PLAYERS_PAGE_SIZE
import com.ondrnovy.nbaplayers.data.PlayerRepository
import com.ondrnovy.nbaplayers.presentation.model.toUiState
import com.ondrnovy.nbaplayers.presentation.pagination.PlayersPagingSource
import kotlinx.coroutines.flow.map

class ListOfPlayersViewModel(
    val playerRepository: PlayerRepository,
) : ViewModel() {

    private val pager = Pager(
        config = PagingConfig(pageSize = PLAYERS_PAGE_SIZE),
        pagingSourceFactory = {
            PlayersPagingSource(playerRepository = playerRepository)
        }
    )
    val playersPagingDataFlow = pager.flow.cachedIn(viewModelScope).map {
        it.map { it.toUiState() }
    }
}