package com.retroent.moviebuff.features.popularmovies


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest


@Composable
internal fun PopularMoviesScreen(
    viewModel: PopularMoviesViewModel = hiltViewModel()
) {

    val state = viewModel.popularMovies.collectAsLazyPagingItems()

    /*when (state.value) {
        PopularScreenUiState.StartLoading -> {}
        PopularScreenUiState.StopLoading -> {}
        is PopularScreenUiState.Result -> {
            val moviesData = state.value as PopularScreenUiState.Result
            DisplayMovies(moviesData.data)
        }
    }*/

    DisplayMovies(state)
}

@Composable
fun DisplayMovies(data: LazyPagingItems<PopularMovieResult>) {
    LazyColumn(
        modifier = Modifier
            .padding(5.dp)
    ) {
       items(items = data, key = {it.id}){
           if (it != null) {
               MovieRow(item = it)
           }
       }
        when(data.loadState.append){
            is LoadState.Error ->{
                item{
                    Text(text = "Hey you have got the error")
                }
            }
            else -> {}
        }
    }

}

@Composable
fun MovieRow(item: PopularMovieResult) {
    Box(modifier = Modifier.padding(5.dp)){
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://image.tmdb.org/t/p/w300${item.imageUrl}")
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)
                )
                Column {
                    Text(text = item.title)
                    Text(text = "Releasing On:${item.releaseDate}")
                    Text(text = "Popularity:${item.popularity}")
                }
            }
        }

        Box(contentAlignment= Alignment.Center,
            modifier = Modifier
                .background(Color.Black, shape = CircleShape)
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
                            (newDiameter - currentWidth) / 2,
                            (newDiameter - currentHeight) / 2
                        )
                    }
                }) {
            Text(
                text = "${((item.voteAverage * 10))}%",
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier.padding(2.dp),
                fontSize = 8.sp
            )
        }

    }
}


/*Box(contentAlignment= Alignment.Center,
modifier = Modifier
.background(Color.Black, shape = CircleShape)
.layout(){ measurable, constraints ->
    // Measure the composable
    val placeable = measurable.measure(constraints)

    //get the current max dimension to assign width=height
    val currentHeight = placeable.height
    val currentWidth = placeable.width
    val newDiameter = maxOf(currentHeight, currentWidth)

    //assign the dimension and the center position
    layout(newDiameter, newDiameter) {
        // Where the composable gets placed
        placeable.placeRelative((newDiameter-currentWidth)/2, (newDiameter-currentHeight)/2)
    }
}) {
    Text(
        text = "20%",
        textAlign = TextAlign.Center,
        color = Color.White,
        modifier = Modifier.padding(1.dp),
    )
}*/


/*Text(
modifier = Modifier
.drawBehind {
    drawCircle(
        color = Color.Red,
        radius = this.size.maxDimension,
    )
},
text = "${((item.voteAverage * 100) / 100)}%",
fontSize = 7.sp
)*/

/*@Preview
@Composable
fun showPreview() {
    DisplayMovies(
        data = PopularMovies(
            1, 2, arrayListOf(
                PopularMovieResult(
                    "/s16H6tpK2utvwDtzZ8Qy4qm5Emw.jpg",
                    1, "The Avtar",
                    2.3,
                    "22-3-1987",
                    34.56
                )
            )
        )
    )
    //MovieRow()
}*/

