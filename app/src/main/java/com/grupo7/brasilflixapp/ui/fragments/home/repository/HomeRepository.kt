package com.grupo7.brasilflixapp.ui.fragments.home.repository

import android.app.Application
import com.grupo7.brasilflixapp.data.api.util.ResponseApi
import com.grupo7.brasilflixapp.data.api.main.RetrofitInstance
import com.grupo7.brasilflixapp.base.BaseRepository
import com.grupo7.brasilflixapp.data.database.movies.allmovies.database.AllMoviesDatabase
import com.grupo7.brasilflixapp.data.database.movies.allmovies.entity.allmovies
import com.grupo7.brasilflixapp.data.database.movies.popular.database.PopularDatabase
import com.grupo7.brasilflixapp.data.database.movies.popular.entity.Popular
import com.grupo7.brasilflixapp.data.database.movies.toprated.database.TopRatedDatabase
import com.grupo7.brasilflixapp.data.database.movies.toprated.entity.TopRated
import com.grupo7.brasilflixapp.data.database.movies.upcoming.database.UpComingDatabase
import com.grupo7.brasilflixapp.data.database.movies.upcoming.entity.UpComing
import com.grupo7.brasilflixapp.ui.model.films.*

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

    suspend fun saveUpComingDatabase(movies: List<films>) {
        val upcomingDb: MutableList<UpComing> = mutableListOf()

        movies.forEach {
            upcomingDb.add(it.toUpComingDb())
        }

        UpComingDatabase
            .getDatabase(application)
            .upcomingDao()
            .insertAllUpComing(
                upcomingDb
            )
    }

    suspend fun saveTopRatedDatabase(movies: List<films>) {
        val topratedDb: MutableList<TopRated> = mutableListOf()

        movies.forEach {
            topratedDb.add(it.toTopRatedDb())
        }

        TopRatedDatabase
            .getDatabase(application)
            .topratedDao()
            .insertAllTopRated(
                topratedDb
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