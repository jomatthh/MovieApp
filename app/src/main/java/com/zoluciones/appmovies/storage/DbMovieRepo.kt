package com.zoluciones.appmovies.storage

import androidx.lifecycle.LiveData
import com.zoluciones.appmovies.Result
import com.zoluciones.appmovies.model.Movie

interface DbMovieRepo {
    suspend fun fetchMovies(page: Int): Result<List<Movie>>
    suspend fun addMovies(list: List<Movie>, page: Int)
}