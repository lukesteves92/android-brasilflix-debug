package com.grupo7.brasilflixapp.data.database.series.popular.dao

import androidx.room.*
import com.grupo7.brasilflixapp.data.database.series.allseries.entity.allseries
import com.grupo7.brasilflixapp.data.database.series.popular.entity.PopularSeries

@Dao
interface PopularSeriesDao {
    @Query("SELECT * FROM PopularSeries")
    suspend fun getAllPopularSeries(): List<PopularSeries>

    @Query("SELECT * FROM PopularSeries WHERE serieId = :serieId")
    suspend fun loadAllSeriesById(serieId: Int): PopularSeries

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPopularSeries(popularseriesList: List<PopularSeries>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPopularSeries(popularseries: PopularSeries)

    @Delete
    suspend fun delete(popularseries: PopularSeries)
}