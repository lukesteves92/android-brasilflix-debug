package com.grupo7.brasilflixapp.data.database.allseries.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.grupo7.brasilflixapp.data.database.allseries.dao.AllSeriesDao
import com.grupo7.brasilflixapp.data.database.allseries.entity.allseries

object AllSeriesDatabase {

    @Database(entities = [allseries::class], version = 1)
    abstract class DatabaseAllSeries : RoomDatabase() {
        abstract fun allseriesDao(): AllSeriesDao
    }

    fun getDatabase(context: Context) : DatabaseAllSeries {
        return Room.databaseBuilder(
            context,
            DatabaseAllSeries::class.java, "allseries_db"
        ).build()
    }
}