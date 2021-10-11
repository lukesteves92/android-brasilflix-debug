package com.grupo7.brasilflixapp.ui.fragments.videos.usecase

import android.app.Application
import android.util.Log
import com.grupo7.brasilflixapp.data.api.util.ResponseApi
import com.grupo7.brasilflixapp.extensions.getFullYoutubeUrl
import com.grupo7.brasilflixapp.ui.fragments.videos.repository.VideosRepository
import com.grupo7.brasilflixapp.ui.model.videos.Videos
import com.grupo7.brasilflixapp.ui.model.videos.VideosResults

class VideosUseCase(
    private val application: Application
) {

    private val videosRepository = VideosRepository(application)

    suspend fun getMoviesVideos(movieId: Int): ResponseApi {
        return when(val responseApi = videosRepository.getMoviesVideos(movieId)) {
            is ResponseApi.Success -> {
                val films = responseApi.data as? VideosResults
                val result = films?.results?.map {
                    it.key = it.key.getFullYoutubeUrl()
                    it
                }
                return ResponseApi.Success(result)

            }
            is ResponseApi.Error -> {
                responseApi
            }

        }
    }

    suspend fun getSeriesVideos(serieId: Int): ResponseApi {
        return when(val responseApi = videosRepository.getSeriesVideos(serieId)) {
            is ResponseApi.Success -> {
                val Series = responseApi.data as? VideosResults
                val result = Series?.results?.map {
                    it?.key = it?.key?.getFullYoutubeUrl()
                    it
                }
                return ResponseApi.Success(result)
            }
            is ResponseApi.Error -> {
                responseApi
            }

        }
    }

}