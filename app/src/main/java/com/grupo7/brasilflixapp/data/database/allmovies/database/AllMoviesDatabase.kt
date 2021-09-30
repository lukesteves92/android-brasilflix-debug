package com.grupo7.brasilflixapp.data.database.allmovies.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.grupo7.brasilflixapp.data.database.allmovies.dao.AllMoviesDao
import com.grupo7.brasilflixapp.data.database.allmovies.entity.allmovies

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