package com.grupo7.brasilflixapp.ui.fragments.favorites.repository

import android.app.Application
import com.grupo7.brasilflixapp.base.BaseRepository
import com.grupo7.brasilflixapp.data.database.favorites.database.FavoritesDatabase
import com.grupo7.brasilflixapp.data.database.favorites.entity.Favorites
import com.grupo7.brasilflixapp.data.database.favorites.entity.FavoritesSeries
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FavoritesRepository (): BaseRepository(), KoinComponent {

    private val application: Application by inject()

    suspend fun getFavoritesMovieFromDb() =
        FavoritesDatabase.getDatabase(application)
            .favoritesDao().getAllFavorites()

    suspend fun removeFavoritesMovieDb(favorites: Favorites) = FavoritesDatabase.getDatabase(application)
    .favoritesDao().delete(favorites)

    suspend fun getFavoritesSeriesFromDb() =
        FavoritesDatabase.getDatabase(application)
            .favoritesSeriesDao().getAllFavoritesSeries()


    suspend fun removeFavoritesSeriesDb(favorites: FavoritesSeries) = FavoritesDatabase.getDatabase(application)
        .favoritesSeriesDao().delete(favorites)
}
