package com.grupo7.brasilflixapp.model.profile

import android.text.style.ClickableSpan
import com.grupo7.brasilflixapp.util.enumarators.ProfileItemActionEnum

class ItemProfile(val itemTitle: String, val itemDescription: String?, val isClickable: Boolean, val action: ProfileItemActionEnum) {
}