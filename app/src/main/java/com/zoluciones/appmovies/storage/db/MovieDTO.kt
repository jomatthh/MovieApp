package com.zoluciones.appmovies.storage.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zoluciones.appmovies.model.Movie

@Entity(tableName = "tbl_movie")
data class MovieDTO(
    @PrimaryKey
    val id: Long = 0,
    val img: String?,
    val title: String?,
    val vote: Float?,
    val date: String?,
    val overview: String?,
    val page: Int = 0
)
{
    fun toMovie() = Movie(id,title?:"",overview?:"",vote?:0F,img?:"",date?:"")
}
