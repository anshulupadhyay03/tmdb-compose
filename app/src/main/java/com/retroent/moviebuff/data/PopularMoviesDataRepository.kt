package com.retroent.moviebuff.data

import com.retroent.moviebuff.features.popularmovies.PopularMovieResult
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PopularMoviesDataRepository @Inject constructor(private val service: BottomMenuApiService) {

    fun fetchPopularMovies(pageNo:Int) = flow {
         service.getPopularMovies(pageNo).suspendOnSuccess {
             val result = mapToDomain(this.data)
             emit(result)
        }.onError {
            println("Movies error :${this.errorBody}")
        }.onException {
            println("Movies error :${this}")
        }
    }.flowOn(Dispatchers.IO)

    private fun mapToDomain(data: PopularMoviesResponse): List<PopularMovieResult> {
        return data.results.map {
           PopularMovieResult(
                it.posterPath,
                it.id,
                it.title,
                it.popularity,
                it.releaseDate,
                it.voteAverage,
            )
        }
    }

}