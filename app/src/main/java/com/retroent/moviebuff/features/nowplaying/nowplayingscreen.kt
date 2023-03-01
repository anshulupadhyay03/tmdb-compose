package com.retroent.moviebuff.features.nowplaying

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.retroent.moviebuff.features.commonui.DisplayMovies

@Composable
internal fun NowPlayingMovies(
    viewModel: NowPlayingMoviesViewModel = hiltViewModel(),
    onItemClick: (id: Int?) -> Unit
) {
    val state = viewModel.nowPlayingMovies.collectAsLazyPagingItems()
    DisplayMovies(data = state, onItemClick)
}