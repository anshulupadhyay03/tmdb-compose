package com.retroent.moviebuff.features.popularmovies


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest


@Composable
internal fun PopularMoviesScreen(
    viewModel: PopularMoviesViewModel = hiltViewModel()
) {

    val state = viewModel.popularMovies.collectAsState()

    when (state.value) {
        PopularScreenUiState.StartLoading -> {}
        PopularScreenUiState.StopLoading -> {}
        is PopularScreenUiState.Result -> {
            val moviesData = state.value as PopularScreenUiState.Result
            DisplayMovies(moviesData.data)
        }
    }
}

@Composable
fun DisplayMovies(data: PopularMovies) {
    LazyColumn(
        modifier = Modifier
            .padding(5.dp)
    ) {
        items(data.movies, key = { it.id }) { item ->
            MovieRow(item)
        }
    }
}

@Composable
fun MovieRow(item: PopularMovieResult) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://image.tmdb.org/t/p/w300${item.imageUrl}")
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(RectangleShape)
            )
            Column {
                Text(text = item.title)
                Text(text = "Releasing On:${item.releaseDate}")
                Text(text = "Popularity:${item.popularity}")
                Text(text = "Votes:${item.voteAverage.toInt()*10}%")
            }

        }

    }

}

