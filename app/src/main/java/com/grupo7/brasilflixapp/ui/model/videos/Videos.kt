package com.grupo7.brasilflixapp.ui.model.videos

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class Videos(
    @SerializedName("name")
    var name: String?,
    @SerializedName("key")
    var key: String?
)
