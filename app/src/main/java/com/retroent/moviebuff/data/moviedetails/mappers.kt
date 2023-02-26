package com.retroent.moviebuff.data.moviedetails

import com.retroent.moviebuff.domain.moviedetails.MovieDetailsModel
import com.retroent.moviebuff.domain.moviedetails.MovieInfo
import com.retroent.moviebuff.domain.moviedetails.MovieReview
import java.text.SimpleDateFormat
import java.util.*


internal fun mapToDomain(response: MovieDetailsResponse): MovieDetailsModel {
    val title = response.title
    val overview = response.overview
    val releaseDate = response.release_date
    val genres = response.genres.joinToString {
        it.name
    }

    val duration = response.runtime
    val backDropImage = response.backdrop_path
    val vote = response.vote_average
    val posterImage = response.poster_path
    val movieInfo = MovieInfo(
        response.status,
        response.original_language,
        response.budget,
        response.revenue
    )

    return MovieDetailsModel(
        title,
        overview,
        releaseDate,
        genres,
        duration,
        backDropImage,
        vote,
        posterImage,
        movieInfo
    )
}

fun mapUserReviews(reviews: MovieReviewsResponse): List<MovieReview> {
    return reviews.results.map {
        MovieReview(
            it.content
                .replace("\r", "")
                .replace("\n", ""),
            it.author,
            it.updated_at.dateFormat(),
            it.url,
            it.author_details.avatar_path ?: "",
            it.author_details.rating ?: 0
        )
    }
}

private fun String.dateFormat(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMM yy", Locale.getDefault())
    val date = inputFormat.parse(this) ?: return ""
    return outputFormat.format(date)

}