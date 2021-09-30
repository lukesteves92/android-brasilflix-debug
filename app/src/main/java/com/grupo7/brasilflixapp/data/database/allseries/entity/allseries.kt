package com.grupo7.brasilflixapp.data.database.allseries.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class allseries(
    var poster_path: String?,
    var first_air_date: String?,
    var original_name: String?,
    var vote_average: Double?,
    var backdrop_path: String?,
    @PrimaryKey
    @ColumnInfo(name = "serieId")
    val id: Int?,
    val overview: String?
): Parcelable

