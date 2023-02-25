package com.retroent.moviebuff.data

import com.retroent.moviebuff.features.popularmovies.MovieResult
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PopularMoviesDataRepository @Inject constructor(private val service: BottomMenuApiService) {

    fun fetchPopularMovies(pageNo: Int) = flow {
        service.getPopularMovies(pageNo).suspendOnSuccess {
            val result = mapToDomain(this.data)
            emit(result)
        }.onError {
            println("Movies error :${this.errorBody}")
        }.onException {
            println("Movies error :${this}")
        }
    }.flowOn(Dispatchers.IO)

    private fun mapToDomain(data: PUNTMoviesResponse): List<MovieResult> {
        return data.results.map {
            MovieResult(
                it.posterPath,
                it.id,
                it.title,
                it.popularity,
                it.releaseDate,
                it.voteAverage,
            )
        }
    }

    fun fetchUpcomingMovies(pageNo: Int) = flow {
        service.getUpcomingMovies(pageNo).suspendOnSuccess {
            val result = mapToDomain(this.data)
            emit(result)
        }.onError {
            println("Movies error :${this.errorBody}")
        }.onException {
            println("Movies error :${this}")
        }
    }.flowOn(Dispatchers.IO)

    fun fetchNowPlayingMovies(pageNo: Int) = flow {
        service.getNowPlayingMovies(pageNo).suspendOnSuccess {
            val result = mapToDomain(this.data)
            emit(result)
        }.onError {
            println("Movies error :${this.errorBody}")
        }.onException {
            println("Movies error :${this}")
        }
    }.flowOn(Dispatchers.IO)

    fun fetchTopRatedMovies(pageNo: Int) = flow {
        service.getTopRatedMovies(pageNo).suspendOnSuccess {
            val result = mapToDomain(this.data)
            emit(result)
        }.onError {
            println("Movies error :${this.errorBody}")
        }.onException {
            println("Movies error :${this}")
        }
    }.flowOn(Dispatchers.IO)
}