package com.sudansh.flickr.search

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sudansh.flickr.R
import com.sudansh.flickr.data.local.NetworkState
import com.sudansh.flickr.data.local.Photo
import com.sudansh.flickr.data.source.FlickrRepo
import com.sudansh.flickr.data.source.paged.PhotosPagedDataSourceFactory

private const val DEFAULT_SEARCH = "cat"

class SearchViewModel(private val flickrRepo: FlickrRepo) : ViewModel() {

    var searchQuery = MutableLiveData(DEFAULT_SEARCH)

    val resultPhotos: LiveData<PagedList<Photo>>
    val isProgressVisible: LiveData<Boolean>
    val errorMessageRes: LiveData<Int>

    private val networkState = MutableLiveData<NetworkState>()

    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(10)
        .setPageSize(10).build()

    init {

        resultPhotos = Transformations.switchMap(searchQuery) { query ->
            val feedDataFactory =
                PhotosPagedDataSourceFactory(flickrRepo, query, networkState, viewModelScope)
            return@switchMap LivePagedListBuilder(feedDataFactory, pagedListConfig).build()
        }

        isProgressVisible = Transformations.map(networkState) { state ->
            return@map when (state) {
                is NetworkState.Loading -> true
                else -> false
            }
        }

        errorMessageRes = Transformations.map(networkState) { state ->
            return@map when (state) {
                is NetworkState.Error -> R.string.error_no_network
                is NetworkState.EmptyResult -> R.string.error_no_result
                else -> null
            }
        }
    }


}


