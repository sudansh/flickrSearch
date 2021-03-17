package com.sudansh.flickr.di

import com.sudansh.flickr.data.source.FlickrConstants
import com.sudansh.flickr.data.source.DefaultFlickrRepo
import com.sudansh.flickr.data.source.NetworkFlickrDataSource
import com.sudansh.flickr.data.source.FlickrDataSource
import com.sudansh.flickr.data.source.FlickrRepo
import com.sudansh.flickr.utils.CoroutinesDispatcherProvider
import com.sudansh.flickr.utils.NetworkUtils
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single<FlickrRepo> { DefaultFlickrRepo(get()) }
    single { NetworkUtils(androidContext()) }
    single<FlickrDataSource> { NetworkFlickrDataSource(get(), get(), get()) }
    single {
        CoroutinesDispatcherProvider(
            Dispatchers.Main,
            Dispatchers.Default,
            Dispatchers.IO
        )
    }
}

fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().apply {
        connectTimeout(60L, TimeUnit.SECONDS)
        readTimeout(60L, TimeUnit.SECONDS)
    }.build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(FlickrConstants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}