package com.grupo7.brasilflixapp.data.database.movies.popular.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.grupo7.brasilflixapp.data.database.movies.popular.dao.PopularDao
import com.grupo7.brasilflixapp.data.database.movies.popular.entity.Popular

object PopularDatabase {

    @Database(entities = [Popular::class], version = 2)
    abstract class DatabasePopular : RoomDatabase() {
        abstract fun popularDao(): PopularDao
    }

    fun getDatabase(context: Context) : DatabasePopular {
        return Room.databaseBuilder(
            context,
            DatabasePopular::class.java, "popular_db"
        ).build()
    }
}