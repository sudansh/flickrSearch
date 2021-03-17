package com.sudansh.flickr.data.source

import com.sudansh.flickr.data.local.PhotoSearchResult
import com.sudansh.flickr.data.local.Result

/**
 * Repository implementation that retrieves search results only from network. If offline cache should be implemented
 * this class can easily be extended by adding offline data souce.
 */
class DefaultFlickrRepo(private val flickrDataSource: FlickrDataSource) : FlickrRepo {

    override suspend fun search(query: String, page: Int): Result<PhotoSearchResult> {
        return flickrDataSource.search(query, page)
    }

}