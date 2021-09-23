package com.grupo7.brasilflixapp.ui.fragments.home.repository

import android.app.Application
import com.grupo7.brasilflixapp.api.util.ResponseApi
import com.grupo7.brasilflixapp.api.main.RetrofitInstance
import com.grupo7.brasilflixapp.base.BaseRepository
import com.grupo7.brasilflixapp.database.popular.database.PopularDatabase
import com.grupo7.brasilflixapp.database.popular.model.Popular
import com.grupo7.brasilflixapp.model.films.films
import com.grupo7.brasilflixapp.model.films.toPopularDb

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
}