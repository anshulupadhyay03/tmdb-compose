package com.retroent.moviebuff.features.toprated

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.retroent.moviebuff.features.commonui.DisplayMovies


@Composable
internal fun TopRatedMovies(
    viewModel: TopRatedMoviesViewModel = hiltViewModel(),
    onItemClick: (id: Int?) -> Unit
){
   val state = viewModel.topRatedMovies.collectAsLazyPagingItems()
    DisplayMovies(data = state,onItemClick)

}