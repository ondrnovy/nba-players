package com.ondrnovy.nbaplayers.di

import com.ondrnovy.nbaplayers.AppConfig
import com.ondrnovy.nbaplayers.data.PlayerRepository
import com.ondrnovy.nbaplayers.data.TeamRepository
import com.ondrnovy.nbaplayers.presentation.viewmodel.PlayersViewModel
import com.ondrnovy.nbaplayers.presentation.viewmodel.TeamViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module


val koinModule: Module = module {
    single {
        provideRetrofit(
            baseUrl = AppConfig.BASE_URL,
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

    viewModel {
        PlayersViewModel(get())
    }

    viewModel {
        TeamViewModel(get())
    }
}
