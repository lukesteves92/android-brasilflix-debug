package com.grupo7.brasilflixapp.database.popular.dao

import androidx.room.*
import com.grupo7.brasilflixapp.database.favorites.model.Favorites
import com.grupo7.brasilflixapp.database.popular.model.Popular

@Dao
interface PopularDao {

    @Query("SELECT * FROM Popular")
    suspend fun getAllPopular(): List<Popular>

    @Query("SELECT * FROM Popular WHERE movieId = :movieId")
    suspend fun loadPopularById(movieId: Int): List<Popular>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPopular(popularList: List<Popular>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopular(popular: Popular)

    @Delete
    suspend fun delete(popular: Popular)
}