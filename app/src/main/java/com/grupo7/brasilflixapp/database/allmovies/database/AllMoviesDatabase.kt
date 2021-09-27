package com.grupo7.brasilflixapp.database.allmovies.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.grupo7.brasilflixapp.database.allmovies.dao.AllMoviesDao
import com.grupo7.brasilflixapp.database.allmovies.model.allmovies
import com.grupo7.brasilflixapp.database.popular.dao.PopularDao
import com.grupo7.brasilflixapp.database.popular.model.Popular

object AllMoviesDatabase {

    @Database(entities = [allmovies::class], version = 1)
    abstract class DatabaseAllMovies : RoomDatabase() {
        abstract fun allmoviesDao(): AllMoviesDao
    }

    fun getDatabase(context: Context) : DatabaseAllMovies {
        return Room.databaseBuilder(
            context,
            DatabaseAllMovies::class.java, "allmovies_db"
        ).build()
    }
}