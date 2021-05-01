package com.zoluciones.appmovies.storage.remote

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    val results: List<MovieRemote>
)
data class MovieRemote(
    val id: Long?,
    @SerializedName("original_title")
    val title: String?,
    @SerializedName("backdrop_path")
    val imagePath: String?,
    @SerializedName("overview")
    val description: String?,
    @SerializedName("release_date")
    val date: String?,
    @SerializedName("vote_average")
    val vote: Float?
)
