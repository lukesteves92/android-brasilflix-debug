package com.grupo7.brasilflixapp.ui.fragments.favorites.usecase

import android.app.Application
import com.grupo7.brasilflixapp.database.favorites.model.Favorites
import com.grupo7.brasilflixapp.database.favorites.model.FavoritesSeries
import com.grupo7.brasilflixapp.ui.fragments.detail.repository.DetailRepository
import com.grupo7.brasilflixapp.ui.fragments.favorites.repository.FavoritesRepository

class FavoritesUseCase(
    application: Application
) {
    private val favoritesRepository = FavoritesRepository(application)

    suspend fun getFavoritesMovieFromDb() =
        favoritesRepository.getFavoritesMovieFromDb()

    suspend fun removeFavoritesMovieDb(favorites: Favorites) {
        favoritesRepository.removeFavoritesMovieDb(favorites)

    }

    suspend fun getFavoritesSeriesFromDb() =
        favoritesRepository.getFavoritesSeriesFromDb()

    suspend fun removeFavoritesSeriesDb(favorites: FavoritesSeries) {
        favoritesRepository.removeFavoritesSeriesDb(favorites)

    }

}