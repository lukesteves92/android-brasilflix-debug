package com.grupo7.brasilflixapp.ui.model.videos

import com.google.gson.annotations.SerializedName
import com.grupo7.brasilflixapp.ui.model.films.films

data class VideosResults(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("results")
    val results: List<Videos>
)
