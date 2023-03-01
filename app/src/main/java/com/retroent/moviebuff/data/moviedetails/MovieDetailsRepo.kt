package com.retroent.moviebuff.data.moviedetails

import com.retroent.moviebuff.data.BottomMenuApiService
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDetailsRepo @Inject constructor(private val service: BottomMenuApiService) {

    fun getMovieDetails(id: Int) = flow {
        service.getMovieDetails(id).suspendOnSuccess {
            emit(mapToDomain(this.data))
        }.onError {
            println("Movies error :${this.errorBody}")
        }.onException {
            println("Movies error :${this}")
        }
    }

    /*fun getMovieReviews(id: Int) = flow {
        service.getMovieReviews(id).suspendOnSuccess {
            emit(mapUserReviews(this.data))
        }.onError {
            println("Movies error :${this.errorBody}")
        }.onException {
            println("Movies error :${this}")
        }
    }

    fun getKeywords(movieId: Int) = flow {
        service.getKeyWords(movieId).suspendOnSuccess {
            emit(this.data.keywords.map {
                it.name
            })
        }.onError {
            println("Movies error :${this.errorBody}")
        }.onException {
            println("Movies error :${this}")
        }
    }

    fun getTopCasts(movieId: Int) = flow {
        service.getTopCast(movieId).suspendOnSuccess {
            emit(this.data.cast
                .asSequence()
                .filter {
                    it.known_for_department == "Acting"
                }.map {
                    MovieCast(it.original_name, it.profile_path, it.character)
                }.toList()
            )
        }.onError {
            println("Movies error :${this.errorBody}")
        }.onException {
            println("Movies error :${this}")
        }
    }*/
}