package com.grupo7.brasilflixapp.data.database.movies.upcoming.dao

import androidx.room.*
import com.grupo7.brasilflixapp.data.database.movies.popular.entity.Popular
import com.grupo7.brasilflixapp.data.database.movies.upcoming.entity.UpComing

@Dao
interface UpComingDao {
    @Query("SELECT * FROM UpComing")
    suspend fun getAllUpComing(): List<UpComing>

    @Query("SELECT * FROM UpComing WHERE movieId = :movieId")
    suspend fun loadUpComingById(movieId: Int): List<UpComing>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUpComing(upcoming: List<UpComing>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUpComing(upcoming: UpComing)

    @Delete
    suspend fun delete(upcoming: UpComing)
}