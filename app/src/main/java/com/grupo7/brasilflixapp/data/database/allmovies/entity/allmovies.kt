package com.grupo7.brasilflixapp.data.database.allmovies.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class allmovies(
    var poster_path: String?,
    var release_date: String?,
    var title: String?,
    var vote_average: Double?,
    var backdrop_path: String?,
    @PrimaryKey
    @ColumnInfo(name = "movieId")
    val id: Int?,
    val overview: String?
): Parcelable