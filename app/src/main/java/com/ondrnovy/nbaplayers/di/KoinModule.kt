package com.ondrnovy.nbaplayers.di

import com.ondrnovy.nbaplayers.AppConfig
import com.ondrnovy.nbaplayers.data.PlayerRepository
import com.ondrnovy.nbaplayers.data.TeamRepository
import com.ondrnovy.nbaplayers.presentation.pagination.PlayersPagingSource
import com.ondrnovy.nbaplayers.presentation.viewmodel.ListOfPlayersViewModel
import com.ondrnovy.nbaplayers.presentation.viewmodel.PlayerDetailViewModel
import com.ondrnovy.nbaplayers.presentation.viewmodel.TeamDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val koinModule: Module = module {
    single {
        provideOkHttpClient(authToken = AppConfig.API_KEY)
    }

    single {
        provideRetrofit(
            baseUrl = AppConfig.BASE_URL,
            okHttpClient = get()
        )
    }

    single {
        provideApiService(get())
    }

    single {
        PlayerRepository(get())
    }

    single {
        TeamRepository(get())
    }

    single {
        PlayersPagingSource(get())
    }

    viewModel {
        ListOfPlayersViewModel(get())
    }

    viewModel { (playerId: String) ->
        PlayerDetailViewModel(playerId, get())
    }

    viewModel { (teamId: String) ->
        TeamDetailViewModel(teamId, get())
    }
}