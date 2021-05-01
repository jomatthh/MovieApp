package com.zoluciones.appmovies.storage

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.zoluciones.appmovies.Result
import com.zoluciones.appmovies.model.Movie
import com.zoluciones.appmovies.storage.db.MovieDTO
import javax.inject.Inject

class DbMovieRepoImpl @Inject constructor(private val dataSource: DbMovieDataSource) : DbMovieRepo {
    override  suspend fun fetchMovies(page: Int): Result<List<Movie>> {
        return when(val result = dataSource.getMovies(page)){
            is Result.Success->{
                return Result.Success(result.data.map {
                    it.toMovie()
                })
            }
            is Result.Failure->{
                Result.Failure(result.exception)
            }
        }
    }

    override suspend fun addMovies(list: List<Movie>, page: Int) {
        dataSource.add(list.map {
            MovieDTO(it.id, it.imgUrl, it.title, it.vote, it.date, it.description,page)
        })
    }
}