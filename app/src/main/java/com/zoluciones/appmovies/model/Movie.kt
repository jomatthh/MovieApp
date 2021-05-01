package com.zoluciones.appmovies.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class MoviePage(
    val totalPage: Int,
    val totalResult: Int,
    val movies: List<Movie>
)

@Parcelize()
data class Movie(
    val id: Long,
    val title: String,
    val description: String,
    val vote: Float,
    val imgUrl: String,
    val date: String
): Parcelable
