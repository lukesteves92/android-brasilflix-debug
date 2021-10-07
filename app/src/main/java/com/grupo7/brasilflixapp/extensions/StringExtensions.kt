package com.grupo7.brasilflixapp.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun String?.getFullImageUrl() = "https://image.tmdb.org/t/p/w500$this"

fun String?.getFullYoutubeUrl() = "https://www.youtube.com/watch?v=$this"

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun String?.getDateBR(): String {
    val dateList = this?.split("-")
    return if (dateList?.size == 3){
        "${dateList[2]}/${dateList[1]}/${dateList[0]}"
    } else {
        "Sem data"
    }
}

fun String?.getUserID(): String {
    val userList = this?.split(".")
    return if (userList?.size == 5){
        "${userList[5]}"
    } else {
        "Sem UserID"
    }
}