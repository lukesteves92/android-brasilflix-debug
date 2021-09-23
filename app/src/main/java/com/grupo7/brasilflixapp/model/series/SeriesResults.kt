package com.grupo7.brasilflixapp.model.series

import com.google.gson.annotations.SerializedName
import com.grupo7.brasilflixapp.model.films.films

class SeriesResults (
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Series>
    )