package com.zoluciones.appmovies.storage

import com.zoluciones.appmovies.Result
import com.zoluciones.appmovies.storage.remote.MovieResponse
import retrofit2.Response

interface RemoteMovieDataSource {
    suspend fun getMovies(page: Int,key: String): Result<MovieResponse>
}