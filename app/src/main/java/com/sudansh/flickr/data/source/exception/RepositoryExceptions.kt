package com.sudansh.flickr.data.source.exception

class ServerException(msg: String? = null) : Exception(msg)

class ParsingException(msg: String? = null) : Exception(msg)

class NoInternetConnectivityException : Exception()