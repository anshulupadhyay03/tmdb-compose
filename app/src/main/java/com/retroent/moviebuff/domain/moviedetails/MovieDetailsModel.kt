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
)
