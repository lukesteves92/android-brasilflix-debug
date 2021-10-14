package com.grupo7.brasilflixapp.ui.fragments.home.viewmodel

import android.app.Application
import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.grupo7.brasilflixapp.base.BaseViewModel
import com.grupo7.brasilflixapp.ui.fragments.home.usecase.HomeUseCase
import com.grupo7.brasilflixapp.ui.model.films.films
import com.grupo7.brasilflixapp.ui.fragments.home.paging.Popular.DataSourceFactoryPopular
import com.grupo7.brasilflixapp.ui.fragments.home.paging.Popular.PageKeyedDataSourcePopular
import com.grupo7.brasilflixapp.ui.fragments.home.paging.TopRated.DataSourceFactoryTopRated
import com.grupo7.brasilflixapp.ui.fragments.home.paging.TopRated.PageKeyedDataSourceTopRated
import com.grupo7.brasilflixapp.ui.fragments.home.paging.UpComing.DataSourceFactoryUpComing
import com.grupo7.brasilflixapp.ui.fragments.home.paging.UpComing.PageKeyedDataSourceUpComing
import com.grupo7.brasilflixapp.ui.fragments.home.repository.HomeRepository

class HomeViewModel() : BaseViewModel() {

    private val homeUseCase = HomeUseCase()
    private val homeRepository = HomeRepository()

//    <---------------------------------------------------- Setup Page 2 Home - Top Rated-------------------------------------->

    var topRatedPagedList: LiveData<PagedList<films>>? = null
    private var watchMoviesLiveDataSource: LiveData<PageKeyedDataSource<Int, films>>? = null

    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PAGE_SIZE).build()


        val homePageKeyedDataSource = PageKeyedDataSourceTopRated(
            homeUseCase = homeUseCase,
            homeRepository = homeRepository
        )
        val homeDataSourceFactory = DataSourceFactoryTopRated(homePageKeyedDataSource)

        watchMoviesLiveDataSource = homeDataSourceFactory.getLiveDataSource()
        topRatedPagedList = LivePagedListBuilder(homeDataSourceFactory, pagedListConfig)
            .build()

    }

    //    <---------------------------------------------------- Setup Page 2 Home - Up Coming-------------------------------------->

    var upComingPagedList: LiveData<PagedList<films>>? = null
    private var watchMoviesLiveDataSourceUpComing: LiveData<PageKeyedDataSource<Int, films>>? = null

    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PAGE_SIZE).build()


        val homePageKeyedDataSourceUpComing = PageKeyedDataSourceUpComing(
            homeUseCase = homeUseCase,
            homeRepository = homeRepository
        )
        val homeDataSourceFactoryUpComing = DataSourceFactoryUpComing(homePageKeyedDataSourceUpComing)

        watchMoviesLiveDataSourceUpComing = homeDataSourceFactoryUpComing.getLiveDataSource()
        upComingPagedList = LivePagedListBuilder(homeDataSourceFactoryUpComing, pagedListConfig)
            .build()

    }

    //    <---------------------------------------------------- Setup Page 2 Home - Popular-------------------------------------->

    var popularPagedList: LiveData<PagedList<films>>? = null
    private var watchMoviesLiveDataSourcePopular: LiveData<PageKeyedDataSource<Int, films>>? = null

    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PAGE_SIZE).build()


        val homePageKeyedDataSourcePopular = PageKeyedDataSourcePopular(
            homeUseCase = homeUseCase,
            homeRepository = homeRepository
        )
        val homeDataSourceFactoryPopular = DataSourceFactoryPopular(homePageKeyedDataSourcePopular)

        watchMoviesLiveDataSourcePopular = homeDataSourceFactoryPopular.getLiveDataSource()
        popularPagedList = LivePagedListBuilder(homeDataSourceFactoryPopular, pagedListConfig)
            .build()

    }

}