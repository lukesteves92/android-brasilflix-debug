package com.grupo7.brasilflixapp.data.database.movies.toprated.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.grupo7.brasilflixapp.ui.model.films.films
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class TopRated(
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

fun TopRated.tofilmsDb(): films {
    return films(
        id = this.id,
        backdrop_path = this.backdrop_path,
        overview = this.overview,
        poster_path = this.poster_path,
        release_date = this.release_date,
        title = this.title,
        vote_average = this.vote_average
    )
}
