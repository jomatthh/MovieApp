package com.zoluciones.appmovies

sealed class Result<out t> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Failure<out T>(val exception: Exception) : Result<T>()
}
