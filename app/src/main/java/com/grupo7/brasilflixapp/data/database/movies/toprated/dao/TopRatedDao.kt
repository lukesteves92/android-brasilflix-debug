package com.grupo7.brasilflixapp.data.database.movies.toprated.dao

import androidx.room.*
import com.grupo7.brasilflixapp.data.database.movies.popular.entity.Popular
import com.grupo7.brasilflixapp.data.database.movies.toprated.entity.TopRated

@Dao
interface TopRatedDao {
    @Query("SELECT * FROM TopRated")
    suspend fun getAllTopRated(): List<TopRated>

    @Query("SELECT * FROM TopRated WHERE movieId = :movieId")
    suspend fun loadTopRatedById(movieId: Int): List<TopRated>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTopRated(topratedList: List<TopRated>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopRated(toprated: TopRated)

    @Delete
    suspend fun delete(toprated: TopRated)
}