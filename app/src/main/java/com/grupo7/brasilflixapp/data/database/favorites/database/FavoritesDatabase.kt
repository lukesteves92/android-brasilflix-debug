package com.grupo7.brasilflixapp.data.database.favorites.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.grupo7.brasilflixapp.data.database.favorites.dao.FavoritesDao
import com.grupo7.brasilflixapp.data.database.favorites.dao.FavoritesSeriesDao
import com.grupo7.brasilflixapp.data.database.favorites.entity.Favorites
import com.grupo7.brasilflixapp.data.database.favorites.entity.FavoritesSeries

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