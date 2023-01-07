package com.retroent.moviebuff.data

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BottomMenuApiService {

    @GET("3/movie/popular")
    suspend fun getPopularMovies(@Query("page") pageNo:Int) : ApiResponse<PopularMoviesResponse>
}