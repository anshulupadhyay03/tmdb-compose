package com.retroent.moviebuff.features.upcomingmovies

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.retroent.moviebuff.features.popularmovies.PopularMoviesViewModel

@Composable
internal fun UpcomingMovies(
    modifier: Modifier = Modifier,
    viewModel: UpcomingMoviesViewModel = hiltViewModel()
){
    Text(text = "Upcoming movies")
}