package com.grupo7.brasilflixapp.ui.fragments.series.paging.series

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.grupo7.brasilflixapp.model.films.films
import com.grupo7.brasilflixapp.model.series.Series
import com.grupo7.brasilflixapp.ui.fragments.home.paging.Popular.HomePageKeyedDataSourcePopular

class HomeDataSourceFactorySeries(
    private val tmdbDataSourceSeries: HomePageKeyedDataSourceSeries
): DataSource.Factory<Int, Series>() {

    private val tmdbLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, Series>>()
    override fun create(): DataSource<Int, Series> {
        tmdbLiveDataSource.postValue(tmdbDataSourceSeries)
        return tmdbDataSourceSeries
    }

    fun getLiveDataSource() : MutableLiveData<PageKeyedDataSource<Int, Series>> {
        return tmdbLiveDataSource
    }
}