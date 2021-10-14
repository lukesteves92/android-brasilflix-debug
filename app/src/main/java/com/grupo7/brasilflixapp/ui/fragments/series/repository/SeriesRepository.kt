package com.grupo7.brasilflixapp.ui.fragments.series.repository

import android.app.Application
import com.grupo7.brasilflixapp.data.api.util.ResponseApi
import com.grupo7.brasilflixapp.data.api.main.RetrofitInstance
import com.grupo7.brasilflixapp.base.BaseRepository
import com.grupo7.brasilflixapp.data.database.series.allseries.database.AllSeriesDatabase
import com.grupo7.brasilflixapp.data.database.series.allseries.entity.allseries
import com.grupo7.brasilflixapp.data.database.series.ontheair.database.OnTheAirSeriesDatabase
import com.grupo7.brasilflixapp.data.database.series.ontheair.entity.OnTheAirSeries
import com.grupo7.brasilflixapp.data.database.series.popular.database.PopularSeriesDatabase
import com.grupo7.brasilflixapp.data.database.series.popular.entity.PopularSeries
import com.grupo7.brasilflixapp.data.database.series.toprated.database.TopRatedSeriesDatabase
import com.grupo7.brasilflixapp.data.database.series.toprated.entity.TopRatedSeries
import com.grupo7.brasilflixapp.ui.model.series.*
import com.grupo7.brasilflixapp.ui.model.series.toPopularSeriesDb
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SeriesRepository(
) : BaseRepository(), KoinComponent {

    private val application: Application by inject()

    suspend fun getSeries(page: Int): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApiSeries.getSeries(page)
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
            RetrofitInstance.tmdbApiSeries.getSeriesTopRated(page)
        }
    }

    suspend fun getSeriesPopular(page: Int): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApiSeries.getSeriesPopular(page)
        }
    }

    suspend fun saveOnTheAirDatabase(series: List<Series>) {
        val ontheairDb: MutableList<OnTheAirSeries> = mutableListOf()

        series.forEach {
            ontheairDb.add(it.toOnTheAirSeriesDb())
        }

        OnTheAirSeriesDatabase
            .getDatabase(application)
            .ontheairDao()
            .insertAllOnTheAirSeries(
                ontheairDb
            )
    }

    suspend fun savePopularSeriesDatabase(series: List<Series>) {
        val popularDb: MutableList<PopularSeries> = mutableListOf()

        series.forEach {
            popularDb.add(it.toPopularSeriesDb())
        }

        PopularSeriesDatabase
            .getDatabase(application)
            .popularseriesDao()
            .insertAllPopularSeries(
                popularDb
            )
    }

    suspend fun saveTopRatedSeriesDatabase(series: List<Series>) {
        val topratedDb: MutableList<TopRatedSeries> = mutableListOf()

        series.forEach {
            topratedDb.add(it.toTopRatedSeriesDb())
        }

        TopRatedSeriesDatabase
            .getDatabase(application)
            .topratedDao()
            .insertAllTopRatedSeries(
                topratedDb
            )
    }
}