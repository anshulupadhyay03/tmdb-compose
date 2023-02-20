package com.retroent.moviebuff.features.upcomingmovies

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.retroent.moviebuff.features.commonui.DisplayMovies

@Composable
internal fun UpcomingMovies(
    viewModel: UpcomingMoviesViewModel = hiltViewModel()
){

    val state = viewModel.upcomingMovies.collectAsLazyPagingItems()
    DisplayMovies(state){}
}

