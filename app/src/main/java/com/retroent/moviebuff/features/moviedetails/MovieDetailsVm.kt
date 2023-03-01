package com.retroent.moviebuff.features.moviedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.retroent.moviebuff.data.moviedetails.MovieDetailsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MovieDetailsVm @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repo: MovieDetailsRepo
) : ViewModel() {

    private val movieId = savedStateHandle.get<Int>("movieId")

    val movieDetailState: StateFlow<MovieDetailsUiState> =
        repo.getMovieDetails(movieId!!)
            .map {
                return@map MovieDetailsUiState.Success(it)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = MovieDetailsUiState.Loading
            )
}