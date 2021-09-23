package com.grupo7.brasilflixapp.model.series

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import com.grupo7.brasilflixapp.model.films.films

class Series (
    @SerializedName("poster_path")
    var poster_path : String? = null,
    @SerializedName("first_air_date")
    var first_air_date : String? = null,
    @SerializedName("original_name")
    var original_name : String? = null,
    @SerializedName("vote_average")
    var vote_average: Double? = null,
    @SerializedName("backdrop_path")
    var backdrop_path: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("overview")
    val overview: String? = null

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