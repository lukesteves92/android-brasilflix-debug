package com.grupo7.brasilflixapp.ui.fragments.series.repository

import android.app.Application
import com.grupo7.brasilflixapp.data.api.util.ResponseApi
import com.grupo7.brasilflixapp.data.api.main.RetrofitInstance
import com.grupo7.brasilflixapp.base.BaseRepository
import com.grupo7.brasilflixapp.data.database.allseries.database.AllSeriesDatabase
import com.grupo7.brasilflixapp.data.database.allseries.entity.allseries
import com.grupo7.brasilflixapp.ui.model.series.Series
import com.grupo7.brasilflixapp.ui.model.series.toAllSeriesDb

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