package com.grupo7.brasilflixapp.ui.fragments.series.usecase

import android.app.Application
import com.grupo7.brasilflixapp.extensions.getDateBR
import com.grupo7.brasilflixapp.extensions.getFullImageUrl
import com.grupo7.brasilflixapp.ui.model.series.Series
import com.grupo7.brasilflixapp.ui.fragments.series.repository.SeriesRepository
import com.grupo7.brasilflixapp.ui.model.series.SeriesResults

class SeriesUseCase(

) {

    private val seriesRepository = SeriesRepository()

    suspend fun setupSeriesList(list: SeriesResults?): List<Series> {
        return list?.results?.filter {
            it.backdrop_path != null
                    && it.overview != null
                    && it.poster_path != null
                    && it.first_air_date != null
                    && it.original_name != null
                    && it.vote_average != null
        }?.map {
            it.backdrop_path = it.backdrop_path?.getFullImageUrl()
            it.poster_path = it.poster_path?.getFullImageUrl()
            it.first_air_date = it.first_air_date?.getDateBR()
            it
        } ?: listOf()
    }

    suspend fun saveAllSeriesDatabase(series: List<Series>) {
        seriesRepository.saveAllSeriesDatabase(series)
    }

    suspend fun setupSeriesTopRatedList(list: SeriesResults?): List<Series> {
        return list?.results?.filter {
            it.backdrop_path != null
                    && it.overview != null
                    && it.poster_path != null
                    && it.first_air_date != null
                    && it.original_name != null
                    && it.vote_average != null
        }?.map {
            it.backdrop_path = it.backdrop_path?.getFullImageUrl()
            it.poster_path = it.poster_path?.getFullImageUrl()
            it.first_air_date = it.first_air_date?.getDateBR()
            it
        } ?: listOf()
    }

    suspend fun setupSeriesPopularList(list: SeriesResults?): List<Series> {
        return list?.results?.filter {
            it.backdrop_path != null
                    && it.overview != null
                    && it.poster_path != null
                    && it.first_air_date != null
                    && it.original_name != null
                    && it.vote_average != null
        }?.map {
            it.backdrop_path = it.backdrop_path?.getFullImageUrl()
            it.poster_path = it.poster_path?.getFullImageUrl()
            it.first_air_date = it.first_air_date?.getDateBR()
            it
        } ?: listOf()
    }

    suspend fun saveOnTheAirDatabase(series: List<Series>) {
        seriesRepository.saveOnTheAirDatabase(series)
    }

    suspend fun savePopularSeriesDatabase(series: List<Series>) {
        seriesRepository.savePopularSeriesDatabase(series)
    }

    suspend fun saveTopRatedSeriesDatabase(series: List<Series>) {
        seriesRepository.saveTopRatedSeriesDatabase(series)
    }
}