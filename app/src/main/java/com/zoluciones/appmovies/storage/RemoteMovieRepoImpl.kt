package com.zoluciones.appmovies.storage

import com.zoluciones.appmovies.Result
import com.zoluciones.appmovies.model.Movie
import com.zoluciones.appmovies.model.MoviePage
import javax.inject.Inject

class RemoteMovieRepoImpl @Inject constructor(private val dataSource: RemoteMovieDataSource) :
    RemoteMovieRepo {
    companion object {
        const val imgPath = "https://image.tmdb.org/t/p/w500"
    }

    override suspend fun fetchMovies(page: Int, key: String): Result<MoviePage> {
        return when (val result = dataSource.getMovies(page, key)) {
            is Result.Success -> {
                val movies = result.data.results.map {
                    Movie(
                        it.id ?: 0,
                        it.title ?: "",
                        it.description ?: "",
                        it.vote ?: 0F,
                        imgPath + (it.imagePath ?: ""),
                        it.date ?: ""
                    )
                }
                val moviePage = MoviePage(result.data.totalPages, result.data.totalResults, movies)
                Result.Success(moviePage)
            }
            is Result.Failure -> {
                Result.Failure(result.exception)
            }
        }
    }
}