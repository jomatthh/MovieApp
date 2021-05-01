package com.zoluciones.appmovies.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.zoluciones.appmovies.Result
import com.zoluciones.appmovies.model.Movie
import com.zoluciones.appmovies.model.MoviePage
import com.zoluciones.appmovies.storage.DbMovieRepo
import com.zoluciones.appmovies.storage.RemoteMovieRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repoDb: DbMovieRepo,
    private val repoRemote: RemoteMovieRepo
) : ViewModel() {

    fun fetchMovies(page: Int, key: String)  = liveData(Dispatchers.IO) {
        emit(repoRemote.fetchMovies(page,key))
    }

    fun addMovies(movies: List<Movie>, page: Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repoDb.addMovies(movies, page)
            }
        }
    }

    fun fetchLocalMovies(page: Int)=  liveData(Dispatchers.IO){
        emit(repoDb.fetchMovies(page))
    }
}