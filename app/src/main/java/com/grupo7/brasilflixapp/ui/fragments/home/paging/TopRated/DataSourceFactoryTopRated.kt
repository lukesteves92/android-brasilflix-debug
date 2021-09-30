package com.grupo7.brasilflixapp.ui.fragments.home.paging.TopRated

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.grupo7.brasilflixapp.ui.model.films.films

class DataSourceFactoryTopRated(
    private val tmdbDataSourceTopRated: PageKeyedDataSourceTopRated
): DataSource.Factory<Int, films>() {

    private val tmdbLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, films>>()
    override fun create(): DataSource<Int, films> {
        tmdbLiveDataSource.postValue(tmdbDataSourceTopRated)
        return tmdbDataSourceTopRated
    }

    fun getLiveDataSource() : MutableLiveData<PageKeyedDataSource<Int, films>> {
        return tmdbLiveDataSource
    }
}