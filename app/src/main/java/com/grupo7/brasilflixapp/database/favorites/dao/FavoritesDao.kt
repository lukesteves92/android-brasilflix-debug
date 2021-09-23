package com.grupo7.brasilflixapp.database.favorites.dao

import androidx.room.*
import com.grupo7.brasilflixapp.database.favorites.model.Favorites

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM Favorites")
    suspend fun getAllFavorites(): List<Favorites>

    @Query("SELECT * FROM Favorites WHERE id = :Id")
    suspend fun loadFavoritesById(Id: Int): List<Favorites>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFavorites(moviesList: List<Favorites>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorites(movie: Favorites)

    @Delete
    suspend fun delete(favorites: Favorites)
}

