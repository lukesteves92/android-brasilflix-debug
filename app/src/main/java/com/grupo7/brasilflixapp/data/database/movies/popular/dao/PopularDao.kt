package com.grupo7.brasilflixapp.data.database.movies.popular.dao

import androidx.room.*
import com.grupo7.brasilflixapp.data.database.movies.popular.entity.Popular

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