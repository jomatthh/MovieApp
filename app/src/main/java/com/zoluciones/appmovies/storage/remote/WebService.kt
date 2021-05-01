package com.zoluciones.appmovies.storage.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    //https://api.themoviedb.org/3/movie/upcoming?page=2&api_key=f46b58478f489737ad5a4651a4b25079
    @GET("3/movie/upcoming?page=2&api_key=f46b58478f489737ad5a4651a4b25079")
    suspend fun getMovies(
        @Query("page") page: Int,
        @Query("api_key") key: String
    ): Response<MovieResponse>
}