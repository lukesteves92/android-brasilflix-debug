package com.grupo7.brasilflixapp.data.database.allmovies.dao

import androidx.room.*
import com.grupo7.brasilflixapp.data.database.allmovies.entity.allmovies


@Dao
interface AllMoviesDao {

    @Query("SELECT * FROM allmovies")
    suspend fun getAllMovies(): List<allmovies>

    @Query("SELECT * FROM allmovies WHERE movieId = :movieId")
    suspend fun loadAllMoviesById(movieId: Int): allmovies

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllallmovies(allmoviesList: List<allmovies>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(allmovies: allmovies)

    @Delete
    suspend fun delete(allmovies: allmovies)
}