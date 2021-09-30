package com.grupo7.brasilflixapp.data.database.series.popular.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.grupo7.brasilflixapp.data.database.movies.popular.entity.Popular
import com.grupo7.brasilflixapp.ui.model.films.films
import com.grupo7.brasilflixapp.ui.model.series.Series
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class PopularSeries(
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

fun PopularSeries.toSeriesDb(): Series {
    return Series(
        id = this.id,
        backdrop_path = this.backdrop_path,
        overview = this.overview,
        poster_path = this.poster_path,
        first_air_date = this.first_air_date,
        original_name = this.original_name,
        vote_average = this.vote_average
    )
}
