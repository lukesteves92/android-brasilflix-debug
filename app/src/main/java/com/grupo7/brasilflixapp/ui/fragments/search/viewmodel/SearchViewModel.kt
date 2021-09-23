package com.grupo7.brasilflixapp.ui.fragments.search.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.grupo7.brasilflixapp.base.BaseViewModel
import com.grupo7.brasilflixapp.ui.fragments.search.usecase.SearchUseCase
import com.grupo7.brasilflixapp.model.films.films
import kotlinx.coroutines.launch

class SearchViewModel(
    application: Application
) : BaseViewModel(application){

    private val searchUseCase = SearchUseCase()

    private val _onSuccessSearch: MutableLiveData<List<films>> =
        MutableLiveData()
    val onSuccessSearch: LiveData<List<films>>
        get() = _onSuccessSearch

    private val _onErrorSearch: MutableLiveData<Int> =
        MutableLiveData()
    val onErrorSearch: LiveData<Int>
        get() = _onErrorSearch

    private val _onCustomErrorSearch: MutableLiveData<Boolean> =
        MutableLiveData()
    val onCustomErrorSearch: LiveData<Boolean>
        get() = _onCustomErrorSearch


    fun searchMovies(search: String) {
        viewModelScope.launch {
            callApi(
                suspend { searchUseCase.searchMovies(search) },
                onSuccess = {
                    val result = it as? List<*>
                    _onSuccessSearch.postValue(
                        result?.filterIsInstance<films>()
                    )
                },
                onError = {
                    _onCustomErrorSearch.postValue(true)
                }
            )
        }
    }


}