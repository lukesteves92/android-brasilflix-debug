package com.grupo7.brasilflixapp.ui.fragments.series.viewmodel

import android.app.Application
import android.nfc.tech.MifareUltralight
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.grupo7.brasilflixapp.base.BaseViewModel
import com.grupo7.brasilflixapp.model.films.films
import com.grupo7.brasilflixapp.ui.fragments.series.usecase.SeriesUseCase
import com.grupo7.brasilflixapp.model.series.Series
import com.grupo7.brasilflixapp.ui.fragments.home.paging.UpComing.HomeDataSourceFactoryUpComing
import com.grupo7.brasilflixapp.ui.fragments.home.paging.UpComing.HomePageKeyedDataSourceUpComing
import com.grupo7.brasilflixapp.ui.fragments.home.repository.HomeRepository
import com.grupo7.brasilflixapp.ui.fragments.series.paging.series.HomeDataSourceFactorySeries
import com.grupo7.brasilflixapp.ui.fragments.series.paging.series.HomePageKeyedDataSourceSeries
import com.grupo7.brasilflixapp.ui.fragments.series.repository.SeriesRepository
import kotlinx.coroutines.launch

class SeriesViewModel(
    application: Application
) : BaseViewModel(application) {

    private val seriesUseCase = SeriesUseCase()
    private val seriesRepository = SeriesRepository()

    //    <---------------------------------------------------- Setup Page 2 Series - Originals-------------------------------------->

    var seriesPagedList: LiveData<PagedList<Series>>? = null
    private var watchMoviesLiveDataSourceSeries: LiveData<PageKeyedDataSource<Int, Series>>? = null

    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(MifareUltralight.PAGE_SIZE).build()


        val homePageKeyedDataSourceSeries = HomePageKeyedDataSourceSeries(
            seriesUseCase = seriesUseCase, seriesRepository = seriesRepository
        )
        val homeDataSourceFactorySeries = HomeDataSourceFactorySeries(homePageKeyedDataSourceSeries)

        watchMoviesLiveDataSourceSeries = homeDataSourceFactorySeries.getLiveDataSource()
        seriesPagedList = LivePagedListBuilder(homeDataSourceFactorySeries, pagedListConfig)
            .build()

    }


}