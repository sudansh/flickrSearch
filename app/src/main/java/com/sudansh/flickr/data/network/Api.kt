package com.sudansh.flickr.data.network

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface Api {

    @GET(".")
    suspend fun getImages(
        @Query("per_page") perpage: Int = 10,
        @Query("page") page: Int,
        @QueryMap options: Map<String, String>,
        @Query("nojsoncallback") noJson: Int = 1
    ): SearchResult

}

