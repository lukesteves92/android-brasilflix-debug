package com.grupo7.brasilflixapp.ui.fragments.home.paging.UpComing

import android.app.Application
import androidx.paging.PageKeyedDataSource
import com.grupo7.brasilflixapp.api.util.ResponseApi
import com.grupo7.brasilflixapp.model.films.films
import com.grupo7.brasilflixapp.model.films.filmsResults
import com.grupo7.brasilflixapp.ui.fragments.home.repository.HomeRepository
import com.grupo7.brasilflixapp.ui.fragments.home.usecase.HomeUseCase
import com.grupo7.brasilflixapp.util.constants.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomePageKeyedDataSourceUpComing (
    private val homeRepository: HomeRepository,
    private val homeUseCase: HomeUseCase,
    val application: Application
) : PageKeyedDataSource<Int, films>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, films>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val movies: List<films> = getUpComingMovies(Constants.Home.FIRST_PAGE)
            homeUseCase.saveAllMoviesDatabase(movies)
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
                listOf()
            }
        }
    }

}
