package com.grupo7.brasilflixapp.data.database.series.ontheair.dao

import androidx.room.*
import com.grupo7.brasilflixapp.data.database.series.ontheair.entity.OnTheAirSeries


@Dao
interface OnTheAirSeriesDao {

    @Query("SELECT * FROM OnTheAirSeries")
    suspend fun getAllOnTheAirSeries(): List<OnTheAirSeries>

    @Query("SELECT * FROM OnTheAirSeries WHERE serieId = :serieId")
    suspend fun loadAllOnTheAirSeriesById(serieId: Int): OnTheAirSeries

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllOnTheAirSeries(ontheairseriesList: List<OnTheAirSeries>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllOnTheAirSeries(ontheairseries: OnTheAirSeries)

    @Delete
    suspend fun delete(ontheairseries: OnTheAirSeries)
}