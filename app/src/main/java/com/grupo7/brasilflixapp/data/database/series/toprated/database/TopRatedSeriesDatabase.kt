package com.grupo7.brasilflixapp.data.database.series.toprated.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.grupo7.brasilflixapp.data.database.series.allseries.dao.AllSeriesDao
import com.grupo7.brasilflixapp.data.database.series.allseries.entity.allseries
import com.grupo7.brasilflixapp.data.database.series.toprated.dao.TopRatedSeriesDao
import com.grupo7.brasilflixapp.data.database.series.toprated.entity.TopRatedSeries

object TopRatedSeriesDatabase {

    @Database(entities = [TopRatedSeries::class], version = 1)
    abstract class DatabaseTopRatedSeries : RoomDatabase() {
        abstract fun topratedDao(): TopRatedSeriesDao
    }

    fun getDatabase(context: Context) : DatabaseTopRatedSeries {
        return Room.databaseBuilder(
            context,
            DatabaseTopRatedSeries::class.java, "topratedseries_db"
        ).build()
    }
}