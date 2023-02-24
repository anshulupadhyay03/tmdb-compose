package com.retroent.moviebuff.features.moviedetails

import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.retroent.moviebuff.domain.moviedetails.MovieDetailsModel
import com.retroent.moviebuff.domain.moviedetails.MovieInfo
import com.retroent.moviebuff.features.commonui.AddVoteProgressBar
import eu.wewox.textflow.TextFlow
import eu.wewox.textflow.TextFlowObstacleAlignment

@Composable
fun MovieDetailsScreen(
    movieDetailsViewModel: MovieDetailsVm = hiltViewModel()
) {
    val movieDetailState by movieDetailsViewModel.movieDetailState.collectAsState()
    when (movieDetailState) {
        MovieDetailsUiState.Loading -> {
            println("Loading")
        }
        is MovieDetailsUiState.Success -> {
            ShowMovieDetails((movieDetailState as MovieDetailsUiState.Success).movieDetails)
        }
        else -> {
            println("Error")
        }
    }

}

@OptIn(ExperimentalTextApi::class)
@Composable
fun ShowMovieDetails(movieDetailsModel: MovieDetailsModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        ShowImages(movieDetailsModel)
        ShowOverview(movieDetailsModel.overview, movieDetailsModel.vote)
        ShowMovieInfo(movieDetailsModel.movieInfo)
    }
}

@Composable
fun ShowMovieInfo(movieInfo: MovieInfo) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        MovieInfoText("Status",movieInfo.status)

        MovieInfoText("Language",movieInfo.language)

        MovieInfoText("Budget",movieInfo.movieBudget)

        MovieInfoText("Revenue",movieInfo.movieRevenue)
    }
}

@Composable
fun MovieInfoText(title:String,data:String) {
    Column(modifier = Modifier.padding(3.dp)) {
        Text(
            text = title,
            color = Color.DarkGray,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Text(
            text = data,
            color = Color.Gray,
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Composable
@OptIn(ExperimentalTextApi::class)
private fun ShowOverview(overview: String, vote: Double) {
    TextFlow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        text = overview,
        obstacleAlignment = TextFlowObstacleAlignment.TopStart,
        ) {
        AddVoteProgressBar(voteAverage = vote)
    }
}

@Composable
private fun ShowImages(movieDetailsModel: MovieDetailsModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://image.tmdb.org/t/p/original${movieDetailsModel.backDropImage}")
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .alpha(0.3f)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) {
            val visibleState = remember {
                MutableTransitionState(false).apply {
                    targetState = true // start the animation immediately
                }
            }
            val density = LocalDensity.current
            AnimatedVisibility(
                visibleState = visibleState,
                enter = slideInVertically {
                    // Slide in from 40 dp from the top.
                    with(density) { -200.dp.roundToPx() }
                }
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://image.tmdb.org/t/p/original${movieDetailsModel.posterImage}")
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(120.dp)
                        .height(180.dp)
                        .padding(start = 5.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .zIndex(0.7f)
                )
            }
            Column(modifier = Modifier.padding(5.dp)) {
                Text(
                    text = movieDetailsModel.title,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                ShowMovieDetails(
                    movieDetailsModel.releaseDate,
                    movieDetailsModel.genres,
                    movieDetailsModel.duration.toMeaningfulDuration()
                )
            }

        }

    }

}

@Composable
fun ShowMovieDetails(date: String, generes: String, duration: String) {
    val bullet = "\u2022"
    val paragraphStyle = ParagraphStyle(
        textIndent = TextIndent(restLine = 5.sp)
    )
    val textModifier = Modifier
        .fillMaxWidth()

    Text(
        buildAnnotatedString {
            withStyle(
                style = paragraphStyle
            ) {
                append(date)
                append("\t")
                append(bullet)
                append("\t")
                append(generes)
                append("\t")
                append(bullet)
                append("\t")
                append(duration)
            }
        },
        modifier = textModifier,
    )
}

fun Int.toMeaningfulDuration(): String {
    val hours = this / 60
    val minutes = this % 60
    return if (hours == 0) "${minutes}m" else "${hours}h ${minutes}m"
}

@Preview
@Composable
fun UiDetails() {
    MovieDetailsScreen()
}