package com.grupo7.brasilflixapp.model.films

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import com.grupo7.brasilflixapp.database.popular.model.Popular

data class films(
    @SerializedName("poster_path")
    var poster_path: String? = null,
    @SerializedName("release_date")
    var release_date: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("vote_average")
    var vote_average: Double? = null,
    @SerializedName("backdrop_path")
    var backdrop_path: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("overview")
    val overview: String? = null
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
