package com.grupo7.brasilflixapp.ui.model.films

import com.google.gson.annotations.SerializedName


data class filmsResults(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val results: List<films>
)