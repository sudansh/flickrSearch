package com.sudansh.flickr.data.source

import com.sudansh.flickr.data.local.PhotoSearchResult
import com.sudansh.flickr.data.local.Result

interface FlickrRepo {
    suspend fun search(query: String, page: Int = 1): Result<PhotoSearchResult>
}