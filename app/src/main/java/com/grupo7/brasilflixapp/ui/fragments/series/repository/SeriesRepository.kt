package com.grupo7.brasilflixapp.ui.fragments.series.repository

import android.app.Application
import com.grupo7.brasilflixapp.api.util.ResponseApi
import com.grupo7.brasilflixapp.api.main.RetrofitInstance
import com.grupo7.brasilflixapp.base.BaseRepository
import com.grupo7.brasilflixapp.database.allmovies.database.AllMoviesDatabase
import com.grupo7.brasilflixapp.database.allmovies.model.allmovies
import com.grupo7.brasilflixapp.database.allseries.database.AllSeriesDatabase
import com.grupo7.brasilflixapp.database.allseries.model.allseries
import com.grupo7.brasilflixapp.model.films.films
import com.grupo7.brasilflixapp.model.films.toAllMoviesDb
import com.grupo7.brasilflixapp.model.series.Series
import com.grupo7.brasilflixapp.model.series.toAllSeriesDb

class SeriesRepository(
    val application: Application
) : BaseRepository() {

    suspend fun getSeries(page: Int): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApi.getSeries(page)
        }
    }

    suspend fun saveAllSeriesDatabase(series: List<Series>) {
        val allseriesDb: MutableList<allseries> = mutableListOf()

        series.forEach {
            allseriesDb.add(it.toAllSeriesDb())
        }

        AllSeriesDatabase
            .getDatabase(application)
            .allseriesDao()
            .insertAllallseries(
                allseriesDb
            )
    }

    suspend fun getSeriesTopRated(page: Int): ResponseApi{
        return safeApiCall {
            RetrofitInstance.tmdbApi.getSeriesTopRated(page)
        }
    }

    suspend fun getSeriesPopular(page: Int): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApi.getSeriesPopular(page)
        }
    }
}