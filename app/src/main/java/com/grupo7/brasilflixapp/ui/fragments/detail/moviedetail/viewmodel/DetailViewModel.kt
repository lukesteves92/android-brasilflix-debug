package com.grupo7.brasilflixapp.ui.fragments.detail.moviedetail.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.grupo7.brasilflixapp.base.BaseViewModel
import com.grupo7.brasilflixapp.data.database.favorites.entity.Favorites
import com.grupo7.brasilflixapp.data.database.movies.allmovies.entity.allmovies
import com.grupo7.brasilflixapp.ui.fragments.detail.moviedetail.usecase.DetailUseCase
import com.grupo7.brasilflixapp.ui.model.films.films
import com.grupo7.brasilflixapp.ui.model.reviews.AuthorResults
import com.grupo7.brasilflixapp.ui.model.videos.Videos
import kotlinx.coroutines.launch

class DetailViewModel(
    application: Application
): BaseViewModel(application) {

    private val detailUseCase = DetailUseCase(getApplication<Application>())

    private val _onSuccessMovieById: MutableLiveData<films> = MutableLiveData()
    val onSuccessMovieById: LiveData<films>
        get() = _onSuccessMovieById

    private val _onSuccessReviewsMovies: MutableLiveData<List<AuthorResults>> = MutableLiveData()
    val onSuccessReviewsMovies: LiveData<List<AuthorResults>>
        get() = _onSuccessReviewsMovies

    private val _onSuccessMovieDbByIdFromDb: MutableLiveData<allmovies> = MutableLiveData()
    val onSuccessMovieDbByIdFromDb: LiveData<allmovies>
        get() = _onSuccessMovieDbByIdFromDb

    private val _onSuccessMoviesVideos: MutableLiveData<List<Videos>> = MutableLiveData()
    val onSuccessMoviesVideos: LiveData<List<Videos>>
        get() = _onSuccessMoviesVideos

    fun getMoviesVideos(movieId: Int) {
        viewModelScope.launch {
            callApi(
                suspend { detailUseCase.getMoviesVideos(movieId) },
                onSuccess = {
                    val result = it as? List<*>
                    _onSuccessMoviesVideos.postValue(
                        result?.filterIsInstance<Videos>()
                    )
                }
            )
        }
    }


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


    fun getMovieByIdFromDb(movieId: Int) {
        viewModelScope.launch {
            val movieFromDb = detailUseCase.getMovieByIdFromDb(movieId)
            _onSuccessMovieDbByIdFromDb.postValue(movieFromDb)
        }
    }

    fun saveFavoritesDb(favorites: Favorites) {
        viewModelScope.launch {
            detailUseCase.saveFavoritesDb(favorites)

        }
    }



}