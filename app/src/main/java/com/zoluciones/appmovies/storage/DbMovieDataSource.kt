package com.zoluciones.appmovies.storage

import androidx.lifecycle.LiveData
import com.zoluciones.appmovies.Result
import com.zoluciones.appmovies.storage.db.MovieDTO

interface DbMovieDataSource {
    suspend fun getMovies(page: Int): Result<List<MovieDTO>>
    suspend fun add(list: List<MovieDTO>)
}