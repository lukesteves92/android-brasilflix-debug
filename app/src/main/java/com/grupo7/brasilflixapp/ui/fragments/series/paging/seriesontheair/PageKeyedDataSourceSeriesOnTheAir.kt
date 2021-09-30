package com.grupo7.brasilflixapp.ui.fragments.series.paging.seriesontheair

import android.app.Application
import androidx.paging.PageKeyedDataSource
import com.grupo7.brasilflixapp.data.api.util.ResponseApi
import com.grupo7.brasilflixapp.data.database.movies.popular.database.PopularDatabase
import com.grupo7.brasilflixapp.data.database.movies.popular.entity.tofilmsDb
import com.grupo7.brasilflixapp.data.database.series.ontheair.database.OnTheAirSeriesDatabase
import com.grupo7.brasilflixapp.data.database.series.ontheair.entity.toSeriesDb
import com.grupo7.brasilflixapp.ui.model.series.Series
import com.grupo7.brasilflixapp.ui.model.series.SeriesResults
import com.grupo7.brasilflixapp.ui.fragments.series.repository.SeriesRepository
import com.grupo7.brasilflixapp.ui.fragments.series.usecase.SeriesUseCase
import com.grupo7.brasilflixapp.util.constants.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PageKeyedDataSourceSeriesOnTheAir (
    private val seriesRepository: SeriesRepository,
    private val seriesUseCase: SeriesUseCase,
    val application: Application
) : PageKeyedDataSource<Int, Series>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Series>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val series: List<Series> = getSeries(Constants.Home.FIRST_PAGE)
            seriesUseCase.saveAllSeriesDatabase(series)
            seriesUseCase.saveOnTheAirDatabase(series)
            callback.onResult(series, null, Constants.Home.FIRST_PAGE + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Series>) {
        loadData(params.key, params.key - 1, callback)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Series>) {
        loadData(params.key, params.key + 1, callback)
    }

    private fun loadData(page: Int, nextPage: Int, callback: LoadCallback<Int, Series>) {
        CoroutineScope(Dispatchers.IO).launch {
            val series: List<Series> = getSeries(page)
            seriesUseCase.saveAllSeriesDatabase(series)
            seriesUseCase.saveOnTheAirDatabase(series)
            callback.onResult(series, nextPage)
        }

    }
    suspend fun getSeries(page: Int): List<Series>{
        return when (
            val response = seriesRepository.getSeries(page)
        ) {
            is ResponseApi.Success -> {
                val list = response.data as? SeriesResults
                return seriesUseCase.setupSeriesList(list)
            }
            is ResponseApi.Error -> {
                var ontheairDB =  OnTheAirSeriesDatabase
                    .getDatabase(application)
                    .ontheairDao()
                    .getAllOnTheAirSeries()

                return ontheairDB.map {
                    it.toSeriesDb()
                }
            }
        }
    }

}