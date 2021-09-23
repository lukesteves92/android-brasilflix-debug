package com.grupo7.brasilflixapp.ui.fragments.search.repository

import com.grupo7.brasilflixapp.api.main.RetrofitInstance
import com.grupo7.brasilflixapp.api.util.ResponseApi
import com.grupo7.brasilflixapp.base.BaseRepository

class SearchRepository: BaseRepository() {

    suspend fun searchMovies(search: String): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApi.searchMovies(1, search)
        }
    }
}