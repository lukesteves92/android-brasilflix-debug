package com.grupo7.brasilflixapp.ui.fragments.detail.repository

import android.app.Application
import com.grupo7.brasilflixapp.api.main.RetrofitInstance
import com.grupo7.brasilflixapp.api.util.ResponseApi
import com.grupo7.brasilflixapp.base.BaseRepository
import com.grupo7.brasilflixapp.database.allmovies.database.AllMoviesDatabase
import com.grupo7.brasilflixapp.database.allseries.database.AllSeriesDatabase
import com.grupo7.brasilflixapp.ui.fragments.tips.view.PARAM_NUMBER
import com.grupo7.brasilflixapp.util.constants.Constants.Home.FIRST_PAGE

class DetailRepository(
    private val application: Application
): BaseRepository() {

    suspend fun getMovieById(movieId: Int): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApi.getMovieById(movieId)
        }
    }

    suspend fun getSeriesById(serieId: Int): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApi.getSeriesById(serieId)
        }
    }

    suspend fun getReviewsMovies(movieId: Int): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApi.getReviewsMovies(movieId, FIRST_PAGE)
        }
    }

    suspend fun getMovieByIdFromDb(movieId: Int) =
        AllMoviesDatabase.getDatabase(application)
            .allmoviesDao().loadAllMoviesById(movieId)

    suspend fun getSerieByIdFromDb(serieId: Int) =
        AllSeriesDatabase.getDatabase(application)
            .allseriesDao().loadAllSeriesById(serieId)

}