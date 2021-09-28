package com.grupo7.brasilflixapp.database.favorites.dao

import androidx.room.*
import com.grupo7.brasilflixapp.database.favorites.model.Favorites
import com.grupo7.brasilflixapp.database.favorites.model.FavoritesSeries

@Dao
interface FavoritesSeriesDao {

    @Query("SELECT * FROM FavoritesSeries")
    suspend fun getAllFavoritesSeries(): List<FavoritesSeries>

    @Query("SELECT * FROM Favorites WHERE id = :Id")
    suspend fun loadFavoritesSeriesById(Id: Int): List<FavoritesSeries>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFavoritesSeries(moviesList: List<FavoritesSeries>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoritesSeries(movie: FavoritesSeries)

    @Delete
    suspend fun delete(favorites: FavoritesSeries)
}