package com.retroent.moviebuff.data

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface BottomMenuApiService {

    @GET("3/movie/popular")
    suspend fun getPopularMovies() : ApiResponse<PopularMoviesResponse>
}