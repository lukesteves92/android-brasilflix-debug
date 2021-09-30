package com.grupo7.brasilflixapp.data.database.series.popular.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.grupo7.brasilflixapp.data.database.series.allseries.dao.AllSeriesDao
import com.grupo7.brasilflixapp.data.database.series.allseries.entity.allseries
import com.grupo7.brasilflixapp.data.database.series.popular.dao.PopularSeriesDao
import com.grupo7.brasilflixapp.data.database.series.popular.entity.PopularSeries

object PopularSeriesDatabase {

    @Database(entities = [PopularSeries::class], version = 1)
    abstract class DatabasePopularSeries : RoomDatabase() {
        abstract fun popularseriesDao(): PopularSeriesDao
    }

    fun getDatabase(context: Context) : DatabasePopularSeries {
        return Room.databaseBuilder(
            context,
            DatabasePopularSeries::class.java, "popularseries_db"
        ).build()
    }

}