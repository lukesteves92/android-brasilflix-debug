package com.grupo7.brasilflixapp.ui.fragments.series.paging.seriesontheair

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.grupo7.brasilflixapp.ui.model.series.Series

class DataSourceFactorySeriesOnTheAir(
    private val tmdbDataSourceSeriesOnTheAir: PageKeyedDataSourceSeriesOnTheAir
): DataSource.Factory<Int, Series>() {

    private val tmdbLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, Series>>()
    override fun create(): DataSource<Int, Series> {
        tmdbLiveDataSource.postValue(tmdbDataSourceSeriesOnTheAir)
        return tmdbDataSourceSeriesOnTheAir
    }

    fun getLiveDataSource() : MutableLiveData<PageKeyedDataSource<Int, Series>> {
        return tmdbLiveDataSource
    }
}