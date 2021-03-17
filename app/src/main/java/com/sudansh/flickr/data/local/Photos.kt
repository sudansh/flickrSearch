package com.sudansh.flickr.data.local

data class Photos(
    val page: Int,
    val pages: Int,
    val photo: List<Photo>
)