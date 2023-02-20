package com.retroent.moviebuff.features.nowplaying

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.retroent.moviebuff.data.PopularMoviesDataRepository
import com.retroent.moviebuff.features.popularmovies.MovieResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NowPlayingMoviesViewModel @Inject constructor(
    private val popularRepo: PopularMoviesDataRepository
) : ViewModel() {

    val nowPlayingMovies: Flow<PagingData<MovieResult>> = Pager(
        pagingSourceFactory = { NowPlayingMoviePagingSource(popularRepo) },
        config = PagingConfig(pageSize = 20)
    ).flow.cachedIn(viewModelScope)

}
