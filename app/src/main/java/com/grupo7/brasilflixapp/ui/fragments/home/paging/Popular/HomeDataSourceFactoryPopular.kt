package com.grupo7.brasilflixapp.ui.fragments.home.paging.Popular

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.grupo7.brasilflixapp.model.films.films
import com.grupo7.brasilflixapp.ui.fragments.home.paging.TopRated.HomePageKeyedDataSourceTopRated

class HomeDataSourceFactoryPopular (
    private val tmdbDataSourcePopular: HomePageKeyedDataSourcePopular
): DataSource.Factory<Int, films>() {

    private val tmdbLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, films>>()
    override fun create(): DataSource<Int, films> {
        tmdbLiveDataSource.postValue(tmdbDataSourcePopular)
        return tmdbDataSourcePopular
    }

    fun getLiveDataSource() : MutableLiveData<PageKeyedDataSource<Int, films>> {
        return tmdbLiveDataSource
    }
}