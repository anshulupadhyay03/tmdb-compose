package com.retroent.moviebuff.features.toprated

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.retroent.moviebuff.features.upcomingmovies.UpcomingMoviesViewModel

@Composable
internal fun TopRatedMovies(
    modifier: Modifier = Modifier,
    viewModel: TopRatedMoviesViewModel = hiltViewModel()
){
    Text(text = "Top Rated movies")
}