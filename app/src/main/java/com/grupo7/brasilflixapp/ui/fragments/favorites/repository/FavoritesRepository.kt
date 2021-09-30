package com.grupo7.brasilflixapp.ui.fragments.favorites.repository

import android.app.Application
import com.grupo7.brasilflixapp.base.BaseRepository
import com.grupo7.brasilflixapp.data.database.favorites.database.FavoritesDatabase
import com.grupo7.brasilflixapp.data.database.favorites.entity.Favorites
import com.grupo7.brasilflixapp.data.database.favorites.entity.FavoritesSeries

class FavoritesRepository (
    private val application: Application
): BaseRepository() {

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
