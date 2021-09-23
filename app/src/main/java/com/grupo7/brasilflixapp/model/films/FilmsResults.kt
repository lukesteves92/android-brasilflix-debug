package com.grupo7.brasilflixapp.model.films

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class filmsResults(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val results: List<films>
)