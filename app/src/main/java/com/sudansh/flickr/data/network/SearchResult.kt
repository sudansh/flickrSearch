package com.sudansh.flickr.data.network

import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("photos")
    var photos: SearchResponse,

    @SerializedName("stat")
    var stat: String

)

data class SearchResponse(
    @SerializedName("page")
    var page: Int,
    @SerializedName("pages")
    var pages: Int,
    @SerializedName("perpage")
    var perpage: Int,
    @SerializedName("photo")
    var photos: List<Photo>
)

data class Photo(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("secret")
    var secret: String? = null,
    @SerializedName("server")
    var server: String? = null,
    @SerializedName("farm")
    var farm: Int? = 0
)