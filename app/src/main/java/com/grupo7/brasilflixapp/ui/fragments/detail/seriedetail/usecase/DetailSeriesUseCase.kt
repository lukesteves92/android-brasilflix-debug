package com.grupo7.brasilflixapp.ui.fragments.detail.seriedetail.usecase

import android.app.Application
import com.grupo7.brasilflixapp.data.api.util.ResponseApi
import com.grupo7.brasilflixapp.data.database.favorites.entity.FavoritesSeries
import com.grupo7.brasilflixapp.extensions.getDateBR
import com.grupo7.brasilflixapp.extensions.getFullImageUrl
import com.grupo7.brasilflixapp.ui.fragments.detail.seriedetail.repository.DetailSeriesRepository
import com.grupo7.brasilflixapp.ui.model.reviews.ReviewResults
import com.grupo7.brasilflixapp.ui.model.series.Series

class DetailSeriesUseCase (
    private val application: Application
) {

    private val detailSeriesRepository = DetailSeriesRepository(application)

    suspend fun getSeriesById(serieId: Int): ResponseApi {
        return when(val responseApi = detailSeriesRepository.getSeriesById(serieId)) {
            is ResponseApi.Success -> {
                val Series = responseApi.data as? Series
                Series?.backdrop_path = Series?.backdrop_path?.getFullImageUrl()
                Series?.first_air_date = Series?.first_air_date?.getDateBR().toString()
                return ResponseApi.Success(Series)
            }
            is ResponseApi.Error -> {
                responseApi
            }

        }
    }


    suspend fun getSerieByIdFromDb(serieId: Int) =
        detailSeriesRepository.getSerieByIdFromDb(serieId)

    suspend fun saveFavoritesSeriesDb(favorites: FavoritesSeries) =
        detailSeriesRepository.saveFavoritesSeriesDb(favorites)


}