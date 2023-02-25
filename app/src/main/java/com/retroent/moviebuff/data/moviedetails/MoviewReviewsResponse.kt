package com.retroent.moviebuff.data.moviedetails

data class Result(
    val author: String = "",
    val author_details: AuthorDetails,
    val content: String = "",
    val created_at: String = "",
    val id: String = "",
    val updated_at: String = "",
    val url: String = ""
)

data class MovieReviewsResponse(
    val id: Int,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)

data class AuthorDetails(
    val avatar_path: String? = "",
    val name: String = "",
    val rating: Int? = 0,
    val username: String = ""
)