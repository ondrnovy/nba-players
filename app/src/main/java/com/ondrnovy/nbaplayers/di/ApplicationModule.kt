package com.ondrnovy.nbaplayers.di

import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Koin Module with application beans
 */
val applicationModule: Module = module {

    single { provideApiService<UzivatelApiApi>(retrofit = get()) }


}
