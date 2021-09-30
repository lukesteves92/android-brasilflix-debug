package com.grupo7.brasilflixapp.ui.fragments.series.viewmodel

import android.app.Application
import android.nfc.tech.MifareUltralight
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.grupo7.brasilflixapp.base.BaseViewModel
import com.grupo7.brasilflixapp.ui.fragments.series.usecase.SeriesUseCase
import com.grupo7.brasilflixapp.ui.model.series.Series
import com.grupo7.brasilflixapp.ui.fragments.series.paging.series.HomeDataSourceFactorySeries
import com.grupo7.brasilflixapp.ui.fragments.series.paging.series.HomePageKeyedDataSourceSeries
import com.grupo7.brasilflixapp.ui.fragments.series.paging.seriespopular.HomeDataSourceFactorySeriesPopular
import com.grupo7.brasilflixapp.ui.fragments.series.paging.seriespopular.HomePageKeyedDataSourceSeriesPopular
import com.grupo7.brasilflixapp.ui.fragments.series.paging.seriestoprated.HomeDataSourceFactorySeriesTopRated
import com.grupo7.brasilflixapp.ui.fragments.series.paging.seriestoprated.HomePageKeyedDataSourceSeriesTopRated
import com.grupo7.brasilflixapp.ui.fragments.series.repository.SeriesRepository

class SeriesViewModel(
    application: Application
) : BaseViewModel(application) {

    private val seriesUseCase = SeriesUseCase(getApplication())
    private val seriesRepository = SeriesRepository(getApplication<Application>())

    //    <---------------------------------------------------- Setup Page 2 Series - On The Air-------------------------------------->

    var seriesPagedList: LiveData<PagedList<Series>>? = null
    private var watchMoviesLiveDataSourceSeries: LiveData<PageKeyedDataSource<Int, Series>>? = null

    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(MifareUltralight.PAGE_SIZE).build()


        val homePageKeyedDataSourceSeries = HomePageKeyedDataSourceSeries(
            seriesUseCase = seriesUseCase, seriesRepository = seriesRepository, application = application
        )
        val homeDataSourceFactorySeries = HomeDataSourceFactorySeries(homePageKeyedDataSourceSeries)

        watchMoviesLiveDataSourceSeries = homeDataSourceFactorySeries.getLiveDataSource()
        seriesPagedList = LivePagedListBuilder(homeDataSourceFactorySeries, pagedListConfig)
            .build()

    }

    //    <---------------------------------------------------- Setup Page 2 Series - Top Rated-------------------------------------->

    var seriesTopRatedPagedList: LiveData<PagedList<Series>>? = null
    private var watchMoviesLiveDataSourceSeriesTopRated: LiveData<PageKeyedDataSource<Int, Series>>? = null

    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(MifareUltralight.PAGE_SIZE).build()


        val homePageKeyedDataSourceSeriesTopRated = HomePageKeyedDataSourceSeriesTopRated(
            seriesUseCase = seriesUseCase, seriesRepository = seriesRepository, application = application
        )
        val homeDataSourceFactorySeriesTopRated = HomeDataSourceFactorySeriesTopRated(homePageKeyedDataSourceSeriesTopRated)

        watchMoviesLiveDataSourceSeriesTopRated = homeDataSourceFactorySeriesTopRated.getLiveDataSourceTopRated()
        seriesTopRatedPagedList = LivePagedListBuilder(homeDataSourceFactorySeriesTopRated, pagedListConfig)
            .build()

    }

    //    <---------------------------------------------------- Setup Page 2 Series - Popular-------------------------------------->

    var seriesPopularPagedList: LiveData<PagedList<Series>>? = null
    private var watchMoviesLiveDataSourceSeriesPopular: LiveData<PageKeyedDataSource<Int, Series>>? = null

    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(MifareUltralight.PAGE_SIZE).build()


        val homePageKeyedDataSourceSeriesPopular = HomePageKeyedDataSourceSeriesPopular(
            seriesUseCase = seriesUseCase, seriesRepository = seriesRepository, application = application
        )
        val homeDataSourceFactorySeriesPopular = HomeDataSourceFactorySeriesPopular(homePageKeyedDataSourceSeriesPopular)

        watchMoviesLiveDataSourceSeriesPopular = homeDataSourceFactorySeriesPopular.getLiveDataSourcePopular()
        seriesPopularPagedList = LivePagedListBuilder(homeDataSourceFactorySeriesPopular, pagedListConfig)
            .build()

    }

}