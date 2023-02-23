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

    val data = viewModel.popularMovies.collectAsLazyPagingItems()
     DisplayMovies(data) {
         println("Ansh movieId $it")
         navController.navigate("details/$it")
     }
}





