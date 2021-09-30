package com.grupo7.brasilflixapp.ui.fragments.detail.main.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.grupo7.brasilflixapp.base.BaseViewModel
import com.grupo7.brasilflixapp.data.database.allseries.entity.allseries
import com.grupo7.brasilflixapp.data.database.favorites.entity.Favorites
import com.grupo7.brasilflixapp.data.database.favorites.entity.FavoritesSeries
import com.grupo7.brasilflixapp.ui.fragments.detail.main.usecase.DetailSearchUseCase
import com.grupo7.brasilflixapp.ui.model.films.films
import com.grupo7.brasilflixapp.ui.model.reviews.AuthorResults
import com.grupo7.brasilflixapp.ui.model.series.Series
import kotlinx.coroutines.launch

class DetailSearchViewModel(
    application: Application
): BaseViewModel(application) {

    private val detailUseCase = DetailSearchUseCase(getApplication<Application>())

    private val _onSuccessMovieById: MutableLiveData<films> = MutableLiveData()
    val onSuccessMovieById: LiveData<films>
        get() = _onSuccessMovieById

    private val _onSuccessSeriesById: MutableLiveData<Series> = MutableLiveData()
    val onSuccessSeriesById: LiveData<Series>
        get() = _onSuccessSeriesById

    private val _onSuccessReviewsMovies: MutableLiveData<List<AuthorResults>> = MutableLiveData()
    val onSuccessReviewsMovies: LiveData<List<AuthorResults>>
        get() = _onSuccessReviewsMovies

    private val _onSuccessMovieDbByIdFromDb: MutableLiveData<films> = MutableLiveData()
    val onSuccessMovieDbByIdFromDb: LiveData<films>
        get() = _onSuccessMovieDbByIdFromDb

    private val _onSuccessSerieDbByIdFromDb: MutableLiveData<allseries> = MutableLiveData()
    val onSuccessSerieDbByIdFromDb: LiveData<allseries>
        get() = _onSuccessSerieDbByIdFromDb

    fun getMovieById(movieId: Int) {
        viewModelScope.launch {
            callApi(
                suspend { detailUseCase.getMovieById(movieId) },
                onSuccess = {
                    _onSuccessMovieById.postValue(it as? films)
                },

            )
        }
    }
    fun getSeriesById(serieId: Int) {
        viewModelScope.launch {
            callApi(
                suspend { detailUseCase.getSeriesById(serieId) },
                onSuccess = {
                    _onSuccessSeriesById.postValue(it as? Series)
                }
            )
        }
    }

    fun getReviewsMovies(movieId: Int) {
        viewModelScope.launch {
            callApi(
                suspend { detailUseCase.getReviewsMovies(movieId) },
                onSuccess = {
                    val result = it as? List<*>
                    _onSuccessReviewsMovies.postValue(
                        result?.filterIsInstance<AuthorResults>()
                    )
                }
            )
        }
    }

    fun getSerieByIdFromDb(serieId: Int) {
        viewModelScope.launch {
            val serieFromDb = detailUseCase.getSerieByIdFromDb(serieId)
            _onSuccessSerieDbByIdFromDb.postValue(serieFromDb)
        }
    }

    fun saveFavoritesDb(favorites: Favorites) {
        viewModelScope.launch {
            detailUseCase.saveFavoritesDb(favorites)

        }
    }

    fun saveFavoritesSeriesDb(favorites: FavoritesSeries) {
        viewModelScope.launch {
            detailUseCase.saveFavoritesSeriesDb(favorites)
        }
    }

}