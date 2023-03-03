package com.retroent.moviebuff.data.moviedetails

data class MovieImages(
    val backdrops: List<Backdrop> = listOf(),
    val id: Int = 0,
    val posters: List<Poster> = listOf()
) {
    data class Backdrop(
        val aspect_ratio: Double = 0.0,
        val file_path: String = "",
        val height: Int = 0,
        val iso_639_1: String? = "",
        val vote_average: Double = 0.0,
        val vote_count: Int = 0,
        val width: Int = 0
    )

    data class Poster(
        val aspect_ratio: Double = 0.0,
        val file_path: String = "",
        val height: Int = 0,
        val iso_639_1: String = "",
        val vote_average: Double = 0.0,
        val vote_count: Int = 0,
        val width: Int = 0
    )
}