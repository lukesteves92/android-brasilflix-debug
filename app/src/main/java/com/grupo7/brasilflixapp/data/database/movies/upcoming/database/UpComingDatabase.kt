package com.grupo7.brasilflixapp.data.database.movies.upcoming.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.grupo7.brasilflixapp.data.database.movies.popular.dao.PopularDao
import com.grupo7.brasilflixapp.data.database.movies.upcoming.dao.UpComingDao
import com.grupo7.brasilflixapp.data.database.movies.upcoming.entity.UpComing

object UpComingDatabase {
    @Database(entities = [UpComing::class], version = 1)
    abstract class DatabaseUpComing : RoomDatabase() {
        abstract fun upcomingDao(): UpComingDao
    }

    fun getDatabase(context: Context) : DatabaseUpComing {
        return Room.databaseBuilder(
            context,
            DatabaseUpComing::class.java, "upcoming_db"
        ).build()
    }

}