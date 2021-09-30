package com.grupo7.brasilflixapp.ui.model.films

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import com.grupo7.brasilflixapp.data.database.movies.allmovies.entity.allmovies
import com.grupo7.brasilflixapp.data.database.movies.popular.entity.Popular
import com.grupo7.brasilflixapp.data.database.movies.toprated.entity.TopRated
import com.grupo7.brasilflixapp.data.database.movies.upcoming.entity.UpComing

data class films(
    @SerializedName("poster_path")
    var poster_path: String?,
    @SerializedName("release_date")
    var release_date: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("vote_average")
    var vote_average: Double?,
    @SerializedName("backdrop_path")
    var backdrop_path: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("overview")
    var overview: String?
) {

    companion object {
        var DIFF_CALLBACK: DiffUtil.ItemCallback<films> =
            object : DiffUtil.ItemCallback<films>() {
                override fun areItemsTheSame(oldItem: films, newItem: films): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: films, newItem: films): Boolean {
                    return oldItem.id == newItem.id
                }
            }
    }

}

fun films.toPopularDb(): Popular {
    return Popular(
        id = this.id,
        backdrop_path = this.backdrop_path,
        overview = this.overview,
        poster_path = this.poster_path,
        release_date = this.release_date,
        title = this.title,
        vote_average = this.vote_average
    )
}

fun films.toUpComingDb(): UpComing {
    return UpComing(
        id = this.id,
        backdrop_path = this.backdrop_path,
        overview = this.overview,
        poster_path = this.poster_path,
        release_date = this.release_date,
        title = this.title,
        vote_average = this.vote_average
    )
}

fun films.toTopRatedDb(): TopRated {
    return TopRated(
        id = this.id,
        backdrop_path = this.backdrop_path,
        overview = this.overview,
        poster_path = this.poster_path,
        release_date = this.release_date,
        title = this.title,
        vote_average = this.vote_average
    )
}

fun films.toAllMoviesDb(): allmovies {
    return allmovies(
        id = this.id,
        backdrop_path = this.backdrop_path,
        overview = this.overview,
        poster_path = this.poster_path,
        release_date = this.release_date,
        title = this.title,
        vote_average = this.vote_average
    )
}



