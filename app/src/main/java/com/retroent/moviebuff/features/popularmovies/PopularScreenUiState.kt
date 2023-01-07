package com.retroent.moviebuff.features.popularmovies

sealed class PopularScreenUiState {

    object StartLoading : PopularScreenUiState()

    object StopLoading : PopularScreenUiState()

    data class Result(val data: PopularMovies) : PopularScreenUiState()
}
