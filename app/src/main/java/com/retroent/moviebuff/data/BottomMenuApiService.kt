package com.retroent.moviebuff.data

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BottomMenuApiService {
    @GET("3/movie/popular")
    suspend fun getPopularMovies(@Query("page") pageNo:Int) : ApiResponse<PUNTMoviesResponse>

    @GET("3/movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") pageNo:Int) : ApiResponse<PUNTMoviesResponse>

    @GET("3/movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("page") pageNo:Int) : ApiResponse<PUNTMoviesResponse>

    @GET("3/movie/top_rated")
    suspend fun getTopRatedMovies(@Query("page") pageNo:Int) : ApiResponse<PUNTMoviesResponse>

    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") id:Int) : ApiResponse<MovieDetailsResponse>
}