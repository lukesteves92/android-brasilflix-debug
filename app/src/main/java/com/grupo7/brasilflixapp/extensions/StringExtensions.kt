package com.grupo7.brasilflixapp.extensions

fun String?.getFullImageUrl() = "https://image.tmdb.org/t/p/w500$this"

fun String?.getFullYoutubeUrl() = "https://www.youtube.com/watch?v=$this"

fun String.getDateBR(): String {
    val dateList = this?.split("-")
    return if (dateList?.size == 3){
        "${dateList[2]}/${dateList[1]}/${dateList[0]}"
    } else {
        "Sem data"
    }
}