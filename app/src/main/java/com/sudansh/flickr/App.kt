package com.sudansh.flickr

import android.app.Application
import com.sudansh.flickr.di.appModule
import com.sudansh.flickr.di.remoteModule
import com.sudansh.flickr.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            // declare modules
            loadKoinModules(
                listOf(
                    appModule,
                    viewModelModule,
                    remoteModule
                )
            )
        }
    }
}