package com.grupo7.brasilflixapp.data.database.series.toprated.dao

import androidx.room.*
import com.grupo7.brasilflixapp.data.database.series.allseries.entity.allseries
import com.grupo7.brasilflixapp.data.database.series.toprated.entity.TopRatedSeries

@Dao
interface TopRatedSeriesDao {

    @Query("SELECT * FROM TopRatedSeries")
    suspend fun getAllTopRatedSeries(): List<TopRatedSeries>

    @Query("SELECT * FROM TopRatedSeries WHERE serieId = :serieId")
    suspend fun loadAllTopRatedSeriesById(serieId: Int): TopRatedSeries

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTopRatedSeries(topratedseriesList: List<TopRatedSeries>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopRatedSeries(topratedseries: TopRatedSeries)

    @Delete
    suspend fun delete(topratedseries: TopRatedSeries)
}