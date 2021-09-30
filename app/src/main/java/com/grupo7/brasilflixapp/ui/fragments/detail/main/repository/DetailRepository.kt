package com.grupo7.brasilflixapp.ui.fragments.detail.main.repository

import android.app.Application
import com.grupo7.brasilflixapp.data.api.main.RetrofitInstance
import com.grupo7.brasilflixapp.data.api.util.ResponseApi
import com.grupo7.brasilflixapp.base.BaseRepository
import com.grupo7.brasilflixapp.data.database.allmovies.database.AllMoviesDatabase
import com.grupo7.brasilflixapp.data.database.allseries.database.AllSeriesDatabase
import com.grupo7.brasilflixapp.data.database.favorites.database.FavoritesDatabase
import com.grupo7.brasilflixapp.data.database.favorites.entity.Favorites
import com.grupo7.brasilflixapp.data.database.favorites.entity.FavoritesSeries
import com.grupo7.brasilflixapp.util.constants.Constants.Home.FIRST_PAGE

class DetailRepository(
    private val application: Application
) : BaseRepository() {

    suspend fun getMovieById(movieId: Int): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApi.getMovieById(movieId)
        }
    }

    suspend fun getSeriesById(serieId: Int): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApi.getSeriesById(serieId)
        }
    }

    suspend fun getReviewsMovies(movieId: Int): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApi.getReviewsMovies(movieId, FIRST_PAGE)
        }
    }

    suspend fun getMovieByIdFromDb(movieId: Int) =
        AllMoviesDatabase.getDatabase(application)
            .allmoviesDao().loadAllMoviesById(movieId)

    suspend fun getSerieByIdFromDb(serieId: Int) =
        AllSeriesDatabase.getDatabase(application)
            .allseriesDao().loadAllSeriesById(serieId)

    suspend fun saveFavoritesDb(favorites: Favorites) =
        FavoritesDatabase.getDatabase(
            application
        ).favoritesDao().insertFavorites(favorites)

    suspend fun saveFavoritesSeriesDb(favorites: FavoritesSeries) =
        FavoritesDatabase.getDatabase(
            application
        ).favoritesSeriesDao().insertFavoritesSeries(favorites)


}