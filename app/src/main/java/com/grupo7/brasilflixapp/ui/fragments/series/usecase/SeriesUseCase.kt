package com.grupo7.brasilflixapp.ui.fragments.series.usecase

import com.grupo7.brasilflixapp.api.util.ResponseApi
import com.grupo7.brasilflixapp.extensions.getDateBR
import com.grupo7.brasilflixapp.extensions.getFullImageUrl
import com.grupo7.brasilflixapp.model.films.films
import com.grupo7.brasilflixapp.model.films.filmsResults
import com.grupo7.brasilflixapp.model.series.Series
import com.grupo7.brasilflixapp.ui.fragments.series.repository.SeriesRepository
import com.grupo7.brasilflixapp.model.series.SeriesResults

class SeriesUseCase {

    private val seriesRepository = SeriesRepository()

    fun setupSeriesList(list: SeriesResults?): List<Series> {
        return list?.results?.map {
            it.poster_path = it.poster_path?.getFullImageUrl()
            it.first_air_date = it.first_air_date?.getDateBR()
            it
        } ?: listOf()
    }
}