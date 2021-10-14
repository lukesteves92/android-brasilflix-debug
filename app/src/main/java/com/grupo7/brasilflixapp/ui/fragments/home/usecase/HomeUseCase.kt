package com.grupo7.brasilflixapp.ui.fragments.home.usecase

import android.app.Application
import com.grupo7.brasilflixapp.extensions.getDateBR
import com.grupo7.brasilflixapp.extensions.getFullImageUrl
import com.grupo7.brasilflixapp.ui.model.films.films
import com.grupo7.brasilflixapp.ui.fragments.home.repository.HomeRepository
import com.grupo7.brasilflixapp.ui.model.films.filmsResults

class HomeUseCase(

) {
    private val homeRepository = HomeRepository()

    fun setupTopRatedList(list: filmsResults?): List<films> {
        return list?.results?.filter{
            it.backdrop_path != null
                    && it.overview != null
                    && it.poster_path != null
                    && it.release_date != null
                    && it.title != null
                    && it.vote_average != null
        }?.map {
            it.backdrop_path = it.backdrop_path?.getFullImageUrl()
            it.poster_path = it.poster_path?.getFullImageUrl()
            it.release_date = it.release_date?.getDateBR()
            it
        } ?: listOf()
    }

    fun setupUpComingList(list: filmsResults?): List<films> {
        return list?.results?.filter {
            it.backdrop_path != null
                    && it.overview != null
                    && it.poster_path != null
                    && it.release_date != null
                    && it.title != null
                    && it.vote_average != null
        }?.map {
            it.backdrop_path = it.backdrop_path?.getFullImageUrl()
            it.poster_path = it.poster_path?.getFullImageUrl()
            it.release_date = it.release_date?.getDateBR()
            it
        } ?: listOf()
    }


    fun setupPopularList(list: filmsResults?): List<films> {
        return list?.results?.filter{
            it.backdrop_path != null
                    && it.overview != null
                    && it.poster_path != null
                    && it.release_date != null
                    && it.title != null
                    && it.vote_average != null
        }?.map {
            it.backdrop_path = it.backdrop_path?.getFullImageUrl()
            it.poster_path = it.poster_path?.getFullImageUrl()
            it.release_date = it.release_date?.getDateBR()
            it
        } ?: listOf()
    }

    suspend fun savePopularDatabase(movies: List<films>) {
        homeRepository.savePopularDatabase(movies)
    }

    suspend fun saveTopRatedDatabase(movies: List<films>) {
        homeRepository.saveTopRatedDatabase(movies)
    }

    suspend fun saveUpComingDatabase(movies: List<films>) {
        homeRepository.saveUpComingDatabase(movies)
    }

    suspend fun saveAllMoviesDatabase(movies: List<films>) {
        homeRepository.saveAllMoviesDatabase(movies)
    }
}
