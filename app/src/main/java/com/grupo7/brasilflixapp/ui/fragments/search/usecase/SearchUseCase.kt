package com.grupo7.brasilflixapp.ui.fragments.search.usecase

import com.grupo7.brasilflixapp.data.api.util.ResponseApi
import com.grupo7.brasilflixapp.extensions.getDateBR
import com.grupo7.brasilflixapp.extensions.getFullImageUrl
import com.grupo7.brasilflixapp.ui.fragments.search.repository.SearchRepository
import com.grupo7.brasilflixapp.ui.model.films.filmsResults
import com.grupo7.brasilflixapp.ui.model.series.SeriesResults

class SearchUseCase {

    private val searchRepository = SearchRepository()

    suspend fun searchMovies(search: String): ResponseApi {
        return when (val responseApi = searchRepository.searchMovies(search)) {
            is ResponseApi.Success -> {
                val data = responseApi.data as filmsResults
                val result = data.results?.map{
                    it.release_date = it.release_date?.getDateBR()
                    it.poster_path = it.poster_path?.getFullImageUrl()
                    it
                }
                ResponseApi.Success(result)
            }
            is ResponseApi.Error -> {
                responseApi
            }
        }
    }

    suspend fun searchSeries(search: String): ResponseApi {
        return when (val responseApi = searchRepository.searchSeries(search)) {
            is ResponseApi.Success -> {
                val data = responseApi.data as SeriesResults
                val result = data.results?.map{
                    it.first_air_date = it.first_air_date?.getDateBR()
                    it.poster_path = it.poster_path?.getFullImageUrl()
                    it
                }
                ResponseApi.Success(result)
            }
            is ResponseApi.Error -> {
                responseApi
            }
        }
    }


}