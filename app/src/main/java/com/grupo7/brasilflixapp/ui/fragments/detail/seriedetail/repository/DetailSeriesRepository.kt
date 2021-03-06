package com.grupo7.brasilflixapp.ui.fragments.detail.seriedetail.repository

import android.app.Application
import com.grupo7.brasilflixapp.base.BaseRepository
import com.grupo7.brasilflixapp.data.api.main.RetrofitInstance
import com.grupo7.brasilflixapp.data.api.util.ResponseApi
import com.grupo7.brasilflixapp.data.database.favorites.database.FavoritesDatabase
import com.grupo7.brasilflixapp.data.database.favorites.entity.Favorites
import com.grupo7.brasilflixapp.data.database.favorites.entity.FavoritesSeries
import com.grupo7.brasilflixapp.data.database.movies.allmovies.database.AllMoviesDatabase
import com.grupo7.brasilflixapp.data.database.series.allseries.database.AllSeriesDatabase
import com.grupo7.brasilflixapp.util.constants.Constants
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DetailSeriesRepository () : BaseRepository(), KoinComponent {

    private val application: Application by inject()

    suspend fun getSeriesById(serieId: Int): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApiSeries.getSeriesById(serieId)
        }
    }

    suspend fun getReviewsSeries(serieId: Int): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApiSeries.getReviewsSeries(serieId, Constants.Home.FIRST_PAGE)
        }
    }

    suspend fun getSerieByIdFromDb(serieId: Int) =
        AllSeriesDatabase.getDatabase(application)
            .allseriesDao().loadAllSeriesById(serieId)

    suspend fun saveFavoritesSeriesDb(favorites: FavoritesSeries) =
        FavoritesDatabase.getDatabase(
            application
        ).favoritesSeriesDao().insertFavoritesSeries(favorites)


    suspend fun getSeriesVideos(seriesId: Int): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApiSeries.getSeriesVideos(seriesId)
        }
    }

}