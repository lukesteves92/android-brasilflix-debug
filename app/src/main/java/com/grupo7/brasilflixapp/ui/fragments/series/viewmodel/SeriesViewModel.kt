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
import com.grupo7.brasilflixapp.ui.fragments.series.paging.seriesontheair.DataSourceFactorySeriesOnTheAir
import com.grupo7.brasilflixapp.ui.fragments.series.paging.seriesontheair.PageKeyedDataSourceSeriesOnTheAir
import com.grupo7.brasilflixapp.ui.fragments.series.paging.seriespopular.DataSourceFactorySeriesPopular
import com.grupo7.brasilflixapp.ui.fragments.series.paging.seriespopular.PageKeyedDataSourceSeriesPopular
import com.grupo7.brasilflixapp.ui.fragments.series.paging.seriestoprated.DataSourceFactorySeriesTopRated
import com.grupo7.brasilflixapp.ui.fragments.series.paging.seriestoprated.PageKeyedDataSourceSeriesTopRated
import com.grupo7.brasilflixapp.ui.fragments.series.repository.SeriesRepository

class SeriesViewModel() : BaseViewModel() {

    private val seriesUseCase = SeriesUseCase()
    private val seriesRepository = SeriesRepository()

    //    <---------------------------------------------------- Setup Page 2 Series - On The Air-------------------------------------->

    var seriesPagedList: LiveData<PagedList<Series>>? = null
    private var watchMoviesLiveDataSourceSeries: LiveData<PageKeyedDataSource<Int, Series>>? = null

    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(MifareUltralight.PAGE_SIZE).build()


        val homePageKeyedDataSourceSeries = PageKeyedDataSourceSeriesOnTheAir(
            seriesUseCase = seriesUseCase, seriesRepository = seriesRepository
        )
        val homeDataSourceFactorySeries = DataSourceFactorySeriesOnTheAir(homePageKeyedDataSourceSeries)

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


        val homePageKeyedDataSourceSeriesTopRated = PageKeyedDataSourceSeriesTopRated(
            seriesUseCase = seriesUseCase, seriesRepository = seriesRepository
        )
        val homeDataSourceFactorySeriesTopRated = DataSourceFactorySeriesTopRated(homePageKeyedDataSourceSeriesTopRated)

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


        val homePageKeyedDataSourceSeriesPopular = PageKeyedDataSourceSeriesPopular(
            seriesUseCase = seriesUseCase, seriesRepository = seriesRepository
        )
        val homeDataSourceFactorySeriesPopular = DataSourceFactorySeriesPopular(homePageKeyedDataSourceSeriesPopular)

        watchMoviesLiveDataSourceSeriesPopular = homeDataSourceFactorySeriesPopular.getLiveDataSourcePopular()
        seriesPopularPagedList = LivePagedListBuilder(homeDataSourceFactorySeriesPopular, pagedListConfig)
            .build()

    }

}