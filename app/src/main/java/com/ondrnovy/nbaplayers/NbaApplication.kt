package com.ondrnovy.nbaplayers

import android.app.Application
import androidx.annotation.CallSuper
import com.ondrnovy.nbaplayers.di.koinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

/**
 * Application entrypoint
 *
 */
open class NbaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    /**
     * Is called when the Koin is creating
     *
     * @param instance [KoinApplication] instance
     */
    @CallSuper
    protected open fun onInitKoin(instance: KoinApplication) {
        instance.modules(
            koinModule
        )
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@NbaApplication)
            onInitKoin(this)
        }
    }
}