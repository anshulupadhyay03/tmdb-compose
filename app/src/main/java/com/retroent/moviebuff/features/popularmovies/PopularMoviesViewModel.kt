package com.retroent.moviebuff.features.popularmovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.retroent.moviebuff.data.BottomMenuApiService
import com.retroent.moviebuff.data.PopularMoviesResponse
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val apiService: BottomMenuApiService
) : ViewModel() {

    private val _popularMovies =
        MutableStateFlow<PopularScreenUiState>(PopularScreenUiState.StartLoading)

    val popularMovies = _popularMovies.asStateFlow()

    init {
        fetchPopularMovies()
    }

    private fun fetchPopularMovies() {
        viewModelScope.launch {
            val movies = apiService.getPopularMovies()
            movies.onSuccess {
                _popularMovies.value = PopularScreenUiState.Result(
                    mapToDomain(this.data)
                )
            }.onError {
                println("Movies error :${this.errorBody}")
            }.onException {
                println("Movies error :${this}")
            }
        }
    }

    private fun mapToDomain(data: PopularMoviesResponse): PopularMovies {
        val movieResults = arrayListOf<PopularMovieResult>()
        data.results.forEach {
            val movie = PopularMovieResult(
                it.posterPath,
                it.id,
                it.title,
                it.popularity,
                it.releaseDate,
                it.voteAverage,
            )
            movieResults.add(movie)
        }

        return PopularMovies(data.page, data.totalPages, movieResults)
    }
}