package com.ondrnovy.nbaplayers.di

import com.ondrnovy.nbaplayers.AppConfig
import com.ondrnovy.nbaplayers.data.PlayerRepository
import com.ondrnovy.nbaplayers.presentation.viewmodel.PlayersViewModel
import org.koin.android.ext.koin.androidApplication
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

    viewModel {
        PlayersViewModel(get())
    }
}
