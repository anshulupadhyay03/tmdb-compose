package com.retroent.moviebuff.features.popularmovies

data class MovieResult(
    val imageUrl: String,
    val id: Int,
    val title: String,
    val popularity: Double,
    val releaseDate: String,
    val voteAverage: Double
)