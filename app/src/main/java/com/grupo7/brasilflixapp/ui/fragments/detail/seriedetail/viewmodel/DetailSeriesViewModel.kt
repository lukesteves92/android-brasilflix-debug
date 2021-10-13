package com.grupo7.brasilflixapp.ui.fragments.detail.seriedetail.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.grupo7.brasilflixapp.base.BaseViewModel
import com.grupo7.brasilflixapp.data.database.favorites.entity.FavoritesSeries
import com.grupo7.brasilflixapp.data.database.series.allseries.entity.allseries
import com.grupo7.brasilflixapp.ui.fragments.detail.seriedetail.usecase.DetailSeriesUseCase
import com.grupo7.brasilflixapp.ui.model.reviews.AuthorResults
import com.grupo7.brasilflixapp.ui.model.series.Series
import com.grupo7.brasilflixapp.ui.model.videos.Videos
import kotlinx.coroutines.launch

class DetailSeriesViewModel(
    application: Application
) : BaseViewModel(application) {

    private val detailSeriesUseCase = DetailSeriesUseCase(getApplication<Application>())

    private val _onSuccessSeriesById: MutableLiveData<Series> = MutableLiveData()
    val onSuccessSeriesById: LiveData<Series>
        get() = _onSuccessSeriesById

    private val _onSuccessReviewsSeries: MutableLiveData<List<AuthorResults>> = MutableLiveData()
    val onSuccessReviewsSeries: LiveData<List<AuthorResults>>
        get() = _onSuccessReviewsSeries

    private val _onSuccessSerieDbByIdFromDb: MutableLiveData<allseries> = MutableLiveData()
    val onSuccessSerieDbByIdFromDb: LiveData<allseries>
        get() = _onSuccessSerieDbByIdFromDb

    private val _onSuccessSeriesVideos: MutableLiveData<List<Videos>> = MutableLiveData()
    val onSuccessSeriesVideos: LiveData<List<Videos>>
        get() = _onSuccessSeriesVideos

    fun getSeriesVideos(seriesId: Int) {
        viewModelScope.launch {
            callApi(
                suspend { detailSeriesUseCase.getSeriesVideos(seriesId) },
                onSuccess = {
                    val result = it as? List<*>
                    _onSuccessSeriesVideos.postValue(
                        result?.filterIsInstance<Videos>()
                    )
                }
            )
        }
    }

    fun getSeriesById(serieId: Int) {
        viewModelScope.launch {
            callApi(
                suspend { detailSeriesUseCase.getSeriesById(serieId) },
                onSuccess = {
                    _onSuccessSeriesById.postValue(it as? Series)
                }
            )
        }
    }

    fun getSerieByIdFromDb(serieId: Int) {
        viewModelScope.launch {
            val serieFromDb = detailSeriesUseCase.getSerieByIdFromDb(serieId)
            _onSuccessSerieDbByIdFromDb.postValue(serieFromDb)
        }
    }

    fun saveFavoritesSeriesDb(favorites: FavoritesSeries) {
        viewModelScope.launch {
            detailSeriesUseCase.saveFavoritesSeriesDb(favorites)
        }
    }

    fun getReviewsSeries(seriesId: Int) {
        viewModelScope.launch {
            callApi(
                suspend { detailSeriesUseCase.getReviewsSeries(seriesId) },
                onSuccess = {
                    val result = it as? List<*>
                    _onSuccessReviewsSeries.postValue(
                        result?.filterIsInstance<AuthorResults>()
                    )
                }
            )
        }
    }

}