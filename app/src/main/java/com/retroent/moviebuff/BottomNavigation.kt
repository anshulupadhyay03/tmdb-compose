package com.retroent.moviebuff

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.retroent.moviebuff.features.nowplaying.NowPlayingMovies
import com.retroent.moviebuff.features.popularmovies.PopularMoviesScreen
import com.retroent.moviebuff.features.toprated.TopRatedMovies
import com.retroent.moviebuff.features.upcomingmovies.UpcomingMovies

data class TabDetails(
    val route: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val iconTextId: Int
)

val BOTTOM_LEVEL_NAVIGATION = listOf(
    TabDetails(
        route = "bottomMenu/popular",
        selectedIcon = R.drawable.baseline_home_filled,
        unselectedIcon = R.drawable.outline_home,
        iconTextId = R.string.popular
    ),
    TabDetails(
        route = "bottomMenu/upcoming",
        selectedIcon = R.drawable.baseline_home_filled,
        unselectedIcon = R.drawable.outline_home,
        iconTextId = R.string.Upcoming
    ),
    TabDetails(
        route = "bottomMenu/now_playing",
        selectedIcon = R.drawable.baseline_home_filled,
        unselectedIcon = R.drawable.outline_home,
        iconTextId = R.string.Now_playing
    ),
    TabDetails(
        route = "bottomMenu/top_rated",
        selectedIcon = R.drawable.baseline_home_filled,
        unselectedIcon = R.drawable.outline_home,
        iconTextId = R.string.Top_rated
    )
)

fun NavGraphBuilder.bottomNavGraph(navController: NavController) {
    navigation(startDestination = BOTTOM_LEVEL_NAVIGATION[0].route, route = "home") {
        composable(BOTTOM_LEVEL_NAVIGATION[0].route) {
            PopularMoviesScreen {
                navigateToMovieDetails(navController, it)
            }
        }
        composable(BOTTOM_LEVEL_NAVIGATION[1].route) { UpcomingMovies{
            navigateToMovieDetails(navController, it)
        } }
        composable(BOTTOM_LEVEL_NAVIGATION[2].route) { NowPlayingMovies{
            navigateToMovieDetails(navController, it)
        }}
        composable(BOTTOM_LEVEL_NAVIGATION[3].route) { TopRatedMovies{
            navigateToMovieDetails(navController, it)
        } }
    }
}

private fun navigateToMovieDetails(navController: NavController, id: Int?) {
    println("Ansh movieId $id")
    navController.navigate("details/$id")
}
