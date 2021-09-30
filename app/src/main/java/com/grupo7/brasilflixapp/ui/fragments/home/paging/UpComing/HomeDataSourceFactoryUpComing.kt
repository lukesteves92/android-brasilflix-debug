package com.grupo7.brasilflixapp.ui.fragments.home.paging.UpComing

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.grupo7.brasilflixapp.ui.model.films.films

class HomeDataSourceFactoryUpComing (
    private val tmdbDataSourceUpComing: HomePageKeyedDataSourceUpComing
): DataSource.Factory<Int, films>() {

    private val tmdbLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, films>>()
    override fun create(): DataSource<Int, films> {
        tmdbLiveDataSource.postValue(tmdbDataSourceUpComing)
        return tmdbDataSourceUpComing
    }

    fun getLiveDataSource() : MutableLiveData<PageKeyedDataSource<Int, films>> {
        return tmdbLiveDataSource
    }
}
