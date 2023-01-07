package com.retroent.moviebuff.features.popularmovies

data class PopularMovieResult(
    val imageUrl: String,
    val id: Int,
    val title: String,
    val popularity: Double,
    val releaseDate: String,
    val voteAverage: Double
)

data class PopularMovies(val page:Int,val totalPages:Int,val movies:List<PopularMovieResult>)