package com.grupo7.brasilflixapp.ui.fragments.series.paging.seriestoprated

import android.app.Application
import androidx.paging.PageKeyedDataSource
import com.grupo7.brasilflixapp.data.api.util.ResponseApi
import com.grupo7.brasilflixapp.ui.model.series.Series
import com.grupo7.brasilflixapp.ui.model.series.SeriesResults
import com.grupo7.brasilflixapp.ui.fragments.series.repository.SeriesRepository
import com.grupo7.brasilflixapp.ui.fragments.series.usecase.SeriesUseCase
import com.grupo7.brasilflixapp.util.constants.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomePageKeyedDataSourceSeriesTopRated (
    private val seriesRepository: SeriesRepository,
    private val seriesUseCase: SeriesUseCase,
    val application: Application
) : PageKeyedDataSource<Int, Series>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Series>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val movies: List<Series> = getSeriesTopRated(Constants.Home.FIRST_PAGE)
            seriesUseCase.saveAllSeriesDatabase(movies)
            callback.onResult(movies, null, Constants.Home.FIRST_PAGE + 1)
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
            val series: List<Series> = getSeriesTopRated(page)
            seriesUseCase.saveAllSeriesDatabase(series)
            callback.onResult(series, nextPage)
        }

    }
    suspend fun getSeriesTopRated(page: Int): List<Series>{
        return when (
            val response = seriesRepository.getSeriesTopRated(page)
        ) {
            is ResponseApi.Success -> {
                val list = response.data as? SeriesResults
                return seriesUseCase.setupSeriesTopRatedList(list)
            }
            is ResponseApi.Error -> {
                listOf()
            }
        }
    }

}