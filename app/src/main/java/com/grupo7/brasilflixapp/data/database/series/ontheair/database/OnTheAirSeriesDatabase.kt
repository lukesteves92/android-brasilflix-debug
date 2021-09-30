package com.grupo7.brasilflixapp.data.database.series.ontheair.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.grupo7.brasilflixapp.data.database.series.ontheair.dao.OnTheAirSeriesDao
import com.grupo7.brasilflixapp.data.database.series.ontheair.entity.OnTheAirSeries


object OnTheAirSeriesDatabase {

    @Database(entities = [OnTheAirSeries::class], version = 1)
    abstract class DatabaseOnTheAirSeries : RoomDatabase() {
        abstract fun ontheairDao(): OnTheAirSeriesDao
    }

    fun getDatabase(context: Context) : DatabaseOnTheAirSeries {
        return Room.databaseBuilder(
            context,
            DatabaseOnTheAirSeries::class.java, "ontheairseries_db"
        ).build()
    }
}