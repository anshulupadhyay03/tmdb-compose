package com.retroent.moviebuff.features.moviedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.retroent.moviebuff.data.PopularMoviesDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MovieDetailsVm @Inject constructor(
    savedStateHandle: SavedStateHandle,
    popularRepo: PopularMoviesDataRepository
) : ViewModel() {

    private val movieId = savedStateHandle.get<Int>("movieId")

     val movieDetailState:StateFlow<MovieDetailsUiState> =
        popularRepo.getMovieDetails(movieId!!)
            .map {
               return@map MovieDetailsUiState.Success(it)
            }
        .stateIn(
       scope =  viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = MovieDetailsUiState.Loading

    )

}