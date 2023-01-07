package com.retroent.moviebuff.features.nowplaying

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NowPlayingMoviesViewModel @Inject constructor():ViewModel() {

    init {
        println("Now playing view model init")
    }
}
