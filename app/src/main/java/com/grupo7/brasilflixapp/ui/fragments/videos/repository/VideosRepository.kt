package com.grupo7.brasilflixapp.ui.fragments.videos.repository

import android.app.Application
import com.grupo7.brasilflixapp.base.BaseRepository
import com.grupo7.brasilflixapp.data.api.main.RetrofitInstance
import com.grupo7.brasilflixapp.data.api.util.ResponseApi

class VideosRepository(): BaseRepository()  {

    suspend fun getMoviesVideos(movieId: Int): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApiMovies.getMoviesVideos(movieId)
        }
    }

    suspend fun getSeriesVideos(serieId: Int): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApiSeries.getSeriesVideos(serieId)
        }
    }
}