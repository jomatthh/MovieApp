package com.zoluciones.appmovies.storage

import com.zoluciones.appmovies.Result
import com.zoluciones.appmovies.model.Movie
import com.zoluciones.appmovies.model.MoviePage

interface RemoteMovieRepo {
    suspend fun fetchMovies(page: Int, key: String): Result<MoviePage>
}