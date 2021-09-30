package com.grupo7.brasilflixapp.ui.fragments.search.repository

import com.grupo7.brasilflixapp.data.api.main.RetrofitInstance
import com.grupo7.brasilflixapp.data.api.util.ResponseApi
import com.grupo7.brasilflixapp.base.BaseRepository

class SearchRepository: BaseRepository() {

    suspend fun searchMovies(search: String): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApi.searchMovies(search)
        }
    }

    suspend fun searchSeries(search: String): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApi.searchSeries(search)
        }
    }
}