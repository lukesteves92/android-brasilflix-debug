package com.grupo7.brasilflixapp.ui.fragments.videos.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.grupo7.brasilflixapp.base.BaseViewModel
import com.grupo7.brasilflixapp.ui.fragments.videos.usecase.VideosUseCase
import com.grupo7.brasilflixapp.ui.model.videos.Videos
import kotlinx.coroutines.launch

class VideosViewModel(): BaseViewModel()  {

    private val videosUseCase = VideosUseCase()

    private val _onSuccessSeriesVideos: MutableLiveData<List<Videos>> = MutableLiveData()
    val onSuccessSeriesVideos: LiveData<List<Videos>>
        get() = _onSuccessSeriesVideos

    private val _onSuccessMoviesVideos: MutableLiveData<List<Videos>> = MutableLiveData()
    val onSuccessMoviesVideos: LiveData<List<Videos>>
        get() = _onSuccessMoviesVideos

    fun getMoviesVideos(movieId: Int) {
        viewModelScope.launch {
            callApi(
                suspend { videosUseCase.getMoviesVideos(movieId) },
                onSuccess = {
                    val result = it as? List<*>
                    _onSuccessMoviesVideos.postValue(
                        result?.filterIsInstance<Videos>()
                    )
                }
            )
        }
    }
    fun getSeriesVideos(serieId: Int) {
        viewModelScope.launch {
            callApi(
                suspend { videosUseCase.getSeriesVideos(serieId) },
                onSuccess = {
                    val result = it as? List<*>
                    _onSuccessSeriesVideos.postValue(
                        result?.filterIsInstance<Videos>()
                    )
                }
            )
        }
    }



}