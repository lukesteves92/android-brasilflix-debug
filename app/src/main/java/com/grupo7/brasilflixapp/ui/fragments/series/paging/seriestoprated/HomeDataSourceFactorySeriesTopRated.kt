package com.grupo7.brasilflixapp.ui.fragments.series.paging.seriestoprated

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.grupo7.brasilflixapp.model.series.Series
import com.grupo7.brasilflixapp.ui.fragments.series.paging.series.HomePageKeyedDataSourceSeries

class HomeDataSourceFactorySeriesTopRated (
    private val tmdbDataSourceSeriesTopRated: HomePageKeyedDataSourceSeriesTopRated
): DataSource.Factory<Int, Series>() {

    private val tmdbLiveDataSourceTopRated = MutableLiveData<PageKeyedDataSource<Int, Series>>()
    override fun create(): DataSource<Int, Series> {
        tmdbLiveDataSourceTopRated.postValue(tmdbDataSourceSeriesTopRated)
        return tmdbDataSourceSeriesTopRated
    }

    fun getLiveDataSourceTopRated() : MutableLiveData<PageKeyedDataSource<Int, Series>> {
        return tmdbLiveDataSourceTopRated
    }
}