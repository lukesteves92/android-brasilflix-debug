package com.grupo7.brasilflixapp.data.database.movies.toprated.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.grupo7.brasilflixapp.data.database.movies.popular.dao.PopularDao
import com.grupo7.brasilflixapp.data.database.movies.popular.entity.Popular
import com.grupo7.brasilflixapp.data.database.movies.toprated.dao.TopRatedDao
import com.grupo7.brasilflixapp.data.database.movies.toprated.entity.TopRated

object TopRatedDatabase {
    @Database(entities = [TopRated::class], version = 1)
    abstract class DatabaseTopRated : RoomDatabase() {
        abstract fun topratedDao(): TopRatedDao
    }

    fun getDatabase(context: Context) : DatabaseTopRated {
        return Room.databaseBuilder(
            context,
            DatabaseTopRated::class.java, "toprated_db"
        ).build()
    }
}