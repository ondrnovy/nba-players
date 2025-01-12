package com.ondrnovy.nbaplayers.di

import com.ondrnovy.nbaplayers.AppConfig
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Koin Module with common beans
 */
val coreModule: Module = module {
    single {
        provideRetrofit(
            baseUrl = AppConfig.BASE_URL,
        )
    }
}
