package com.retroent.moviebuff.domain.moviedetails

data class MovieDetailsModel(
    val title: String,
    val overview: String,
    val releaseDate: String,
    val genres: String,
    val duration: Int,
    val backDropImage: String,
    val vote: Double,
    val posterImage: String,
    val movieInfo: MovieInfo
)

data class MovieInfo(
    val status: String,
    val language: String,
    private val budget: Long,
    private val revenue: Long
) {
    val movieBudget
        get() = if (budget > 0) "$${budget}" else "--"

    val movieRevenue
        get() = if (revenue > 0) "$${revenue}" else "--"
}

data class MovieReview(
    val content: String,
    val title: String,
    val updatedAt: String,
    val url: String,
    val avatarPath: String,
    val rating: Int
)




