package com.grupo7.brasilflixapp.data.database.favorites.dao

import androidx.room.*
import com.grupo7.brasilflixapp.data.database.favorites.entity.FavoritesSeries

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