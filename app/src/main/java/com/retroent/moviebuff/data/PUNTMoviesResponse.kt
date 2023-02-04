package com.retroent.moviebuff.data


import com.google.gson.annotations.SerializedName

//common reponse class for popular,upcoming,Now_playing and top_rated movies
data class PUNTMoviesResponse(
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("results")
    val results: List<Result> = listOf(),
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0
)