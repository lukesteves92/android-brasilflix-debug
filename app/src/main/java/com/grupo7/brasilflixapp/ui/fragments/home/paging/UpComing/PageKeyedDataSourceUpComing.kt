package com.grupo7.brasilflixapp.ui.fragments.home.paging.UpComing

import android.app.Application
import androidx.paging.PageKeyedDataSource
import com.grupo7.brasilflixapp.data.api.util.ResponseApi
import com.grupo7.brasilflixapp.data.database.movies.popular.database.PopularDatabase
import com.grupo7.brasilflixapp.data.database.movies.popular.entity.tofilmsDb
import com.grupo7.brasilflixapp.data.database.movies.upcoming.database.UpComingDatabase
import com.grupo7.brasilflixapp.data.database.movies.upcoming.entity.tofilmsDb
import com.grupo7.brasilflixapp.ui.model.films.films
import com.grupo7.brasilflixapp.ui.model.films.filmsResults
import com.grupo7.brasilflixapp.ui.fragments.home.repository.HomeRepository
import com.grupo7.brasilflixapp.ui.fragments.home.usecase.HomeUseCase
import com.grupo7.brasilflixapp.util.constants.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PageKeyedDataSourceUpComing (
    private val homeRepository: HomeRepository,
    private val homeUseCase: HomeUseCase
) : PageKeyedDataSource<Int, films>(), KoinComponent {

    private val application: Application by inject()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, films>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val movies: List<films> = getUpComingMovies(Constants.Home.FIRST_PAGE)
            homeUseCase.saveAllMoviesDatabase(movies)
            homeUseCase.saveUpComingDatabase(movies)
            callback.onResult(movies, null, Constants.Home.FIRST_PAGE + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, films>) {
        loadData(params.key, params.key - 1, callback)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, films>) {
        loadData(params.key, params.key + 1, callback)
    }

    private fun loadData(page: Int, nextPage: Int, callback: LoadCallback<Int, films>) {
        CoroutineScope(Dispatchers.IO).launch {
            val films: List<films> = getUpComingMovies(page)
            homeUseCase.saveAllMoviesDatabase(films)
            homeUseCase.saveUpComingDatabase(films)
            callback.onResult(films, nextPage)
        }

    }
    suspend fun getUpComingMovies(page: Int): List<films>{
        return when (
            val response = homeRepository.getUpComingMovies(page)
        ) {
            is ResponseApi.Success -> {
                val list = response.data as? filmsResults
                return homeUseCase.setupUpComingList(list)
            }
            is ResponseApi.Error -> {
                var upcomingDB =  UpComingDatabase
                    .getDatabase(application)
                    .upcomingDao()
                    .getAllUpComing()

                return upcomingDB.map {
                    it.tofilmsDb()
                }
            }
        }
    }

}
