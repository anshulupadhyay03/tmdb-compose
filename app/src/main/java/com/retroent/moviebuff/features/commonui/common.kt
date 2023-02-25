package com.retroent.moviebuff.features.commonui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.retroent.moviebuff.features.popularmovies.MovieResult
import com.retroent.moviebuff.ui.theme.LightGreen


@Composable
fun DisplayMovies(data: LazyPagingItems<MovieResult>, onItemClick: (id: Int) -> Unit) {
    LazyColumn(
        modifier = Modifier.padding(5.dp).background(Color.LightGray),
        state = data.rememberLazyListState()
    ) {
        items(items = data, key = { it.id }) {
            if (it != null) {
                MovieRow(item = it, onItemClick)
            }
        }
        when (data.loadState.append) {
            is LoadState.Error -> {
                item {
                    ErrorItem("Something went wrong!Pleas try again") {
                        data.refresh()
                    }
                }
            }
            is LoadState.Loading -> {
                item {
                    LoadingItem()
                }
            }
            is LoadState.NotLoading -> Unit
        }

        when (data.loadState.refresh) {
            is LoadState.Error -> {
                item {
                    ErrorItem("Something went wrong!Pleas try again") {
                        data.refresh()
                    }
                }
            }
            is LoadState.Loading -> {
                item {
                    LoadingItem()
                }
            }
            is LoadState.NotLoading -> Unit
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieRow(item: MovieResult, onItemClick: (id: Int) -> Unit) {
    Box(modifier = Modifier.padding(5.dp)) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            onClick = { onItemClick(item.id) }
        ) {
            Row {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://image.tmdb.org/t/p/original${item.imageUrl}")
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                )
                Column(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxHeight()
                ) {
                    Text(
                        text = item.title,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Row {
                        Column {
                            Text(text = "Releasing On:${item.releaseDate}")
                            Text(text = "Popularity:${item.popularity}")
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            AddVoteProgressBar(item.voteAverage)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AddVoteProgressBar(voteAverage: Double) {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(Color.Black, shape = CircleShape)
            .padding(1.dp)
            .border(3.dp, LightGreen, CircleShape)
            .layout() { measurable, constraints ->
                // Measure the composable
                val placeable = measurable.measure(constraints)

                //get the current max dimension to assign width=height
                val currentHeight = placeable.height
                val currentWidth = placeable.width
                val newDiameter = maxOf(currentHeight, currentWidth)

                //assign the dimension and the center position
                layout(newDiameter, newDiameter) {
                    // Where the composable gets placed
                    placeable.placeRelative(
                        (newDiameter - currentWidth) / 2, (newDiameter - currentHeight) / 2
                    )
                }

            }) {
        CircularProgressIndicator(
            progress = voteAverage.toFloat() / 10, color = Color.Green,
        )

        Text(
            text = "${(voteAverage * 10).toInt()}%",
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.padding(2.dp),
            fontSize = 12.sp,
            style = TextStyle(
                fontWeight = FontWeight.Bold
            )
        )
    }
}


@Composable
fun ErrorItem(error: String, onRefreshClick: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(5.dp),
        shape = RectangleShape,
        onClick = { onRefreshClick() }) {
        Text(
            textAlign = TextAlign.Center,
            text = error,
            color = Color.Red
        )
    }

}

@Composable
fun LoadingItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .padding(8.dp)
        )
    }
}

@Composable
fun <T : Any> LazyPagingItems<T>.rememberLazyListState(): LazyListState {
    // After recreation, LazyPagingItems first return 0 items, then the cached items.
    // This behavior/issue is resetting the LazyListState scroll position.
    // Below is a workaround. More info: https://issuetracker.google.com/issues/177245496.
    return when (itemCount) {
        // Return a different LazyListState instance.
        0 -> remember(this) { LazyListState(0, 0) }
        // Return rememberLazyListState (normal case).
        else -> androidx.compose.foundation.lazy.rememberLazyListState()
    }
}