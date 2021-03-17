package com.sudansh.flickr.data.local

sealed class NetworkState {
    object Loaded : NetworkState()
    object Loading : NetworkState()
    object EmptyResult : NetworkState()
    data class Error(val msg: String?) : NetworkState()
}
