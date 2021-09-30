package com.grupo7.brasilflixapp.ui.model.series

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import com.grupo7.brasilflixapp.data.database.allseries.entity.allseries

class Series (
    @SerializedName("poster_path")
    var poster_path : String?,
    @SerializedName("first_air_date")
    var first_air_date : String?,
    @SerializedName("original_name")
    var original_name : String?,
    @SerializedName("vote_average")
    var vote_average: Double?,
    @SerializedName("backdrop_path")
    var backdrop_path: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("overview")
    val overview: String?

    ) {

    companion object {
        var DIFF_CALLBACK: DiffUtil.ItemCallback<Series> =
            object : DiffUtil.ItemCallback<Series>() {
                override fun areItemsTheSame(oldItem: Series, newItem: Series): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Series, newItem: Series): Boolean {
                    return oldItem.id == newItem.id
                }
            }
    }

}

fun Series.toAllSeriesDb(): allseries {
    return allseries(
        id = this.id,
        backdrop_path = this.backdrop_path,
        overview = this.overview,
        poster_path = this.poster_path,
        first_air_date = this.first_air_date,
        original_name = this.original_name,
        vote_average = this.vote_average
    )
}