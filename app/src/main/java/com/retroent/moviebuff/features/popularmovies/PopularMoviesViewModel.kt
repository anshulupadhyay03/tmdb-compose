package com.retroent.moviebuff.features.popularmovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.retroent.moviebuff.data.PopularMoviesDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val popularRepo: PopularMoviesDataRepository
) : ViewModel() {

    /* private val _popularMovies =
         MutableStateFlow<PopularScreenUiState>(PopularScreenUiState.StartLoading)

     val popularMovies = _popularMovies.asStateFlow()*/

    val popularMovies: Flow<PagingData<PopularMovieResult>> = Pager(
        pagingSourceFactory = { PopularMoviePagingSource(popularRepo) },
        config = PagingConfig(pageSize = 20)
    ).flow.cachedIn(viewModelScope)
}