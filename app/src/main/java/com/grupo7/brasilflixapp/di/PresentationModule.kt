package com.grupo7.brasilflixapp.di

import android.app.Application
import com.grupo7.brasilflixapp.ui.fragments.home.viewmodel.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PresentationModule {

    val viewModelModules = module {
        viewModel { HomeViewModel() }

    }

}