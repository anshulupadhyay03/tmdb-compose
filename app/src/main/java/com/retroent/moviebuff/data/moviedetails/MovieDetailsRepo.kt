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
}