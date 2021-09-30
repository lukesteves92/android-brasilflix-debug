package com.grupo7.brasilflixapp.ui.fragments.favorites.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.grupo7.brasilflixapp.base.BaseViewModel
import com.grupo7.brasilflixapp.data.database.favorites.entity.Favorites
import com.grupo7.brasilflixapp.data.database.favorites.entity.FavoritesSeries
import com.grupo7.brasilflixapp.ui.fragments.favorites.usecase.FavoritesUseCase
import kotlinx.coroutines.launch

class FavoritesViewModel(
    application: Application
): BaseViewModel(application) {

    private val favoritesUseCase = FavoritesUseCase(getApplication<Application>())

    private val _onSuccessFavoritesMoviesFromDb: MutableLiveData<List<Favorites>> = MutableLiveData()
    val onSuccessFavoritesMoviesFromDb: LiveData<List<Favorites>>
        get() = _onSuccessFavoritesMoviesFromDb

    private val _onSuccessFavoritesSeriesFromDb: MutableLiveData<List<FavoritesSeries>> = MutableLiveData()
    val onSuccessFavoritesSeriesFromDb: LiveData<List<FavoritesSeries>>
        get() = _onSuccessFavoritesSeriesFromDb

    fun getFavoritesMovieFromDb() {
        viewModelScope.launch {
            val movieFromDb = favoritesUseCase.getFavoritesMovieFromDb()
            _onSuccessFavoritesMoviesFromDb.postValue(movieFromDb)
        }
    }

    fun removeFavoritesMovieDb(favorites: Favorites) {
        viewModelScope.launch{
            favoritesUseCase.removeFavoritesMovieDb(favorites)
        }

    }

    fun getFavoritesSeriesFromDb() {
        viewModelScope.launch {
            val serieFromDb = favoritesUseCase.getFavoritesSeriesFromDb()
            _onSuccessFavoritesSeriesFromDb.postValue(serieFromDb)
        }
    }

    fun removeFavoritesSeriesDb(favorites: FavoritesSeries) {
        viewModelScope.launch{
            favoritesUseCase.removeFavoritesSeriesDb(favorites)
        }

    }

}