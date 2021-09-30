package com.grupo7.brasilflixapp.ui.fragments.home.paging.Popular

import android.app.Application
import androidx.paging.PageKeyedDataSource
import com.grupo7.brasilflixapp.data.api.util.ResponseApi
import com.grupo7.brasilflixapp.data.database.movies.popular.database.PopularDatabase
import com.grupo7.brasilflixapp.data.database.movies.popular.entity.tofilmsDb
import com.grupo7.brasilflixapp.ui.model.films.films
import com.grupo7.brasilflixapp.ui.model.films.filmsResults
import com.grupo7.brasilflixapp.ui.fragments.home.repository.HomeRepository
import com.grupo7.brasilflixapp.ui.fragments.home.usecase.HomeUseCase
import com.grupo7.brasilflixapp.util.constants.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomePageKeyedDataSourcePopular(
    private val homeRepository: HomeRepository,
    private val homeUseCase: HomeUseCase,
    val application: Application
) : PageKeyedDataSource<Int, films>() {



    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, films>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val movies: List<films> = getPopularMovies(Constants.Home.FIRST_PAGE)
            homeUseCase.savePopularDatabase(movies)
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
            val films: List<films> = getPopularMovies(page)
            homeUseCase.savePopularDatabase(films)
            homeUseCase.saveAllMoviesDatabase(films)
            callback.onResult(films, nextPage)
        }

    }

    suspend fun getPopularMovies(page: Int): List<films> {

        return when (
            val response = homeRepository.getPopularMovies(page)
        ) {
            is ResponseApi.Success -> {
                val list = response.data as? filmsResults
                return homeUseCase.setupPopularList(list)
            }
            is ResponseApi.Error -> {
                var popularDB =  PopularDatabase
                    .getDatabase(application)
                    .popularDao()
                    .getAllPopular()

                return popularDB.map {
                    it.tofilmsDb()
                }

            }

        }
    }

}