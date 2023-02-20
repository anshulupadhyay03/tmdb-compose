package com.retroent.moviebuff.features.moviedetails

import com.retroent.moviebuff.domain.moviedetails.MovieDetailsModel

sealed interface MovieDetailsUiState {

    object Loading : MovieDetailsUiState

    object Error : MovieDetailsUiState

    data class Success(val movieDetails: MovieDetailsModel) : MovieDetailsUiState
}