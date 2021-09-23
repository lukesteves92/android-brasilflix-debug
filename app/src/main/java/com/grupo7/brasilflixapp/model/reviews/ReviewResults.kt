package com.grupo7.brasilflixapp.model.reviews

import com.google.gson.annotations.SerializedName

data class ReviewResults(
   @SerializedName("page")
    val page: Int,
   @SerializedName("results")
    val results: List<AuthorResults>
   )