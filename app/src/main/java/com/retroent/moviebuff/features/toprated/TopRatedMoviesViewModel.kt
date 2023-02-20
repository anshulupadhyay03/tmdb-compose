package com.retroent.moviebuff.features.toprated

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.retroent.moviebuff.data.PopularMoviesDataRepository
import com.retroent.moviebuff.features.popularmovies.MovieResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TopRatedMoviesViewModel @Inject constructor(
    private val popularRepo: PopularMoviesDataRepository
) :ViewModel(){

    val topRatedMovies: Flow<PagingData<MovieResult>> = Pager(
        pagingSourceFactory = { TopRatedMoviePagingSource(popularRepo) },
        config = PagingConfig(pageSize = 20)
    ).flow.cachedIn(viewModelScope)
}
