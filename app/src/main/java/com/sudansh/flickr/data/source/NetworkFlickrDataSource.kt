package com.sudansh.flickr.data.source

import com.sudansh.flickr.data.local.Photo
import com.sudansh.flickr.data.local.PhotoSearchResult
import com.sudansh.flickr.data.local.Photos
import com.sudansh.flickr.data.local.Result
import com.sudansh.flickr.data.network.Api
import com.sudansh.flickr.data.source.exception.ServerException
import com.sudansh.flickr.data.source.exception.NoInternetConnectivityException
import com.sudansh.flickr.data.source.exception.ParsingException
import com.sudansh.flickr.utils.CoroutinesDispatcherProvider
import com.sudansh.flickr.utils.NetworkUtils
import kotlinx.coroutines.withContext
import org.json.JSONException
import java.io.IOException


class NetworkFlickrDataSource(
    private val networkUtils: NetworkUtils,
    private val ioDispatcher: CoroutinesDispatcherProvider,
    private val api: Api
) : FlickrDataSource {
    val map = HashMap<String, String>()

    init {
        map["method"] = "flickr.photos.search"
        map["api_key"] = "3e7cc266ae2b0e0d78e279ce8e361736"
        map["format"] = "json"

    }

    override suspend fun search(query: String, page: Int): Result<PhotoSearchResult> =
        withContext(ioDispatcher.io) {
            if (!networkUtils.hasNetworkConnection()) {
                return@withContext Result.Error(NoInternetConnectivityException())
            }

            return@withContext try {
                map["text"] = query
                val responseString = api.getImages(page = page, options = map)
                Result.Success(
                    PhotoSearchResult(
                        photos = Photos(
                            page = responseString.photos.page,
                            pages = responseString.photos.pages,
                            photo = responseString.photos.photos.filter { it.id != null }.map {
                                Photo(
                                    id = it.id.orEmpty(),
                                    secret = it.secret.orEmpty(),
                                    server = it.server.orEmpty(),
                                    farm = it.farm ?: 0
                                )
                            }.distinctBy { it.id }
                        ),
                        stat = responseString.stat
                    )
                )
            } catch (io: IOException) {
                Result.Error(ServerException())
            } catch (je: JSONException) {
                Result.Error(ParsingException(je.message))
            } catch (e: Exception) {
                Result.Error(e)
            }

        }

}