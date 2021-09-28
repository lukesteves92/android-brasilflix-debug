package com.grupo7.brasilflixapp.database.favorites.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.grupo7.brasilflixapp.database.favorites.dao.FavoritesDao
import com.grupo7.brasilflixapp.database.favorites.dao.FavoritesSeriesDao
import com.grupo7.brasilflixapp.database.favorites.model.Favorites
import com.grupo7.brasilflixapp.database.favorites.model.FavoritesSeries

object FavoritesDatabase {



    @Database(entities = [Favorites::class, FavoritesSeries::class], version = 2)
    abstract class DatabaseFavoritesMovies : RoomDatabase() {
        abstract fun favoritesDao(): FavoritesDao
        abstract fun favoritesSeriesDao(): FavoritesSeriesDao

    }

    fun getDatabase(context: Context) : DatabaseFavoritesMovies {
        return Room.databaseBuilder(
            context,
            DatabaseFavoritesMovies::class.java, "favoritesMovies_db"
        ).build()
    }
}