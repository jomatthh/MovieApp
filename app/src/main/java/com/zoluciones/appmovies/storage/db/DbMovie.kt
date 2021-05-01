package com.zoluciones.appmovies.storage.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [MovieDTO::class], version = 1)
abstract class DbMovie : RoomDatabase() {
    abstract fun movieDAO(): DbMovieDAO
}