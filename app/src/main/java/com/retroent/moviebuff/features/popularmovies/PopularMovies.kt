package com.retroent.moviebuff.features.popularmovies


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.retroent.moviebuff.features.commonui.DisplayMovies


@Composable
internal fun PopularMoviesScreen(
    viewModel: PopularMoviesViewModel = hiltViewModel(),
    onItemClick: (id: Int?) -> Unit
) {

    val data = viewModel.popularMovies.collectAsLazyPagingItems()
    DisplayMovies(data, onItemClick)
}





