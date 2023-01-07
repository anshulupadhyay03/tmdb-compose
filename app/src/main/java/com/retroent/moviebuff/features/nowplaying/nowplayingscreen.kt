package com.retroent.moviebuff.features.nowplaying

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.retroent.moviebuff.features.upcomingmovies.UpcomingMoviesViewModel

@Composable
internal fun NowPlayingMovies(
    modifier: Modifier = Modifier,
    viewModel: NowPlayingMoviesViewModel = hiltViewModel()
){
    Text(text = "Now Playing movies")
}