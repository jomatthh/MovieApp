package com.zoluciones.appmovies.storage.db

import androidx.lifecycle.LiveData
import com.zoluciones.appmovies.storage.DbMovieDataSource
import javax.inject.Inject
import com.zoluciones.appmovies.Result

class DbMovieDataSourceImpl @Inject constructor(private val dbMovie: DbMovie): DbMovieDataSource {

    private val dbMovieDAO = dbMovie.movieDAO()

    override suspend fun getMovies(page: Int): Result<List<MovieDTO>> {
        return Result.Success(dbMovieDAO.getMovies(page))
    }

    override suspend fun add(list: List<MovieDTO>) {
        dbMovieDAO.insertMovies(list)
    }
}