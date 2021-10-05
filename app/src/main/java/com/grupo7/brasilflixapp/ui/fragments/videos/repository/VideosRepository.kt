package com.grupo7.brasilflixapp.ui.fragments.videos.repository

import android.app.Application
import com.grupo7.brasilflixapp.base.BaseRepository
import com.grupo7.brasilflixapp.data.api.main.RetrofitInstance
import com.grupo7.brasilflixapp.data.api.util.ResponseApi

class VideosRepository(
    val application: Application
): BaseRepository()  {

    suspend fun getMoviesVideos(movieId: Int): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApi.getMoviesVideos(movieId)
        }
    }

    suspend fun getSeriesVideos(serieId: Int): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApi.getSeriesVideos(serieId)
        }
    }



}