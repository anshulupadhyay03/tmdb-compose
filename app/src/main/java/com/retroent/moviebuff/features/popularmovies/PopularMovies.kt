package com.retroent.moviebuff.features.popularmovies


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.retroent.moviebuff.features.commonui.DisplayMovies


@Composable
internal fun PopularMoviesScreen(
    navController: NavController,
    viewModel: PopularMoviesViewModel = hiltViewModel()
) {

    val state = viewModel.popularMovies.collectAsLazyPagingItems()
    DisplayMovies(state) {
        navController.navigate("details/$it")
    }
}




