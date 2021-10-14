package com.grupo7.brasilflixapp.di

import android.app.Application
import com.grupo7.brasilflixapp.ui.fragments.home.usecase.HomeUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DomainModule {

    val useCaseModules = module {
        single { HomeUseCase() }

    }

}