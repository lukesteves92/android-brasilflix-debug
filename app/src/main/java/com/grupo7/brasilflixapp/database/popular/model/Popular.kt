package com.grupo7.brasilflixapp.database.popular.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.grupo7.brasilflixapp.model.films.films
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Popular(
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

fun Popular.tofilmsDb(): films {
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
