package com.sudansh.flickr.data.source.paged

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.sudansh.flickr.data.local.NetworkState
import com.sudansh.flickr.data.local.Photo
import com.sudansh.flickr.data.source.FlickrRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope

class PhotosPagedDataSourceFactory(
    private val repository: FlickrRepo,
    private val searchQuery: String,
    private val networkState: MutableLiveData<NetworkState>,
    private val coroutineScope: CoroutineScope = GlobalScope
) : DataSource.Factory<Int, Photo>() {

    override fun create(): DataSource<Int, Photo> =
        PhotosPagedDataSource(repository, networkState, coroutineScope, searchQuery)

}