package com.grupo7.brasilflixapp.ui.fragments.home.repository

import android.app.Application
import com.grupo7.brasilflixapp.data.api.util.ResponseApi
import com.grupo7.brasilflixapp.data.api.main.RetrofitInstance
import com.grupo7.brasilflixapp.base.BaseRepository
import com.grupo7.brasilflixapp.data.database.allmovies.database.AllMoviesDatabase
import com.grupo7.brasilflixapp.data.database.allmovies.entity.allmovies
import com.grupo7.brasilflixapp.data.database.popular.database.PopularDatabase
import com.grupo7.brasilflixapp.data.database.popular.entity.Popular
import com.grupo7.brasilflixapp.ui.model.films.films
import com.grupo7.brasilflixapp.ui.model.films.toAllMoviesDb
import com.grupo7.brasilflixapp.ui.model.films.toPopularDb

class HomeRepository(
    val application: Application
) : BaseRepository() {

    suspend fun getTopRatedMovies(page: Int): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApi.getFilmes(page)
        }
    }

    suspend fun getUpComingMovies(page: Int): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApi.getUpComing(page)
        }
    }

    suspend fun getPopularMovies(page: Int): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApi.getPopular(page)
        }
    }

    suspend fun savePopularDatabase(movies: List<films>) {
        val popularDb: MutableList<Popular> = mutableListOf()

        movies.forEach {
            popularDb.add(it.toPopularDb())
        }

        PopularDatabase
            .getDatabase(application)
            .popularDao()
            .insertAllPopular(
                popularDb
            )
    }

    suspend fun saveAllMoviesDatabase(movies: List<films>) {
        val allmoviesDb: MutableList<allmovies> = mutableListOf()

        movies.forEach {
            allmoviesDb.add(it.toAllMoviesDb())
        }

        AllMoviesDatabase
            .getDatabase(application)
            .allmoviesDao()
            .insertAllallmovies(
                allmoviesDb
            )
    }
}