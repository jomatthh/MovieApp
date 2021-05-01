package com.zoluciones.appmovies.storage.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DbMovieDAO {
    @Query("SELECT * FROM tbl_movie WHERE page = :currentPage")
    suspend fun getMovies(currentPage: Int): List<MovieDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movieDTO: List<MovieDTO>)
}