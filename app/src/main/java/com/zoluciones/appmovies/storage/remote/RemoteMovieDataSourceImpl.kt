package com.zoluciones.appmovies.storage.remote

import com.zoluciones.appmovies.Result
import com.zoluciones.appmovies.storage.RemoteMovieDataSource
import retrofit2.Response
import javax.inject.Inject

class RemoteMovieDataSourceImpl @Inject constructor(private val webService: WebService): RemoteMovieDataSource {
    override suspend fun getMovies(page: Int, key: String): Result<MovieResponse> {
        return  try {
            val response = webService.getMovies(page,key)
            if(response.isSuccessful){
                response.body()?.let {
                    Result.Success(it)
                }?: run{
                    Result.Failure(Exception("Hubo problemas al cargar"))
                }
            }else{
                Result.Failure(Exception("Hubo problemas al cargar"))
            }
        }catch (ex : Exception){
            Result.Failure(ex)
        }
    }
}