package com.sudansh.flickr.di

import com.sudansh.flickr.data.network.Api
import org.koin.dsl.module

val remoteModule = module {
    factory { createOkHttpClient() }
    factory { createWebService<Api>(get()) }
}