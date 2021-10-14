package com.grupo7.brasilflixapp.di

import com.grupo7.brasilflixapp.ui.fragments.home.repository.HomeRepository
import org.koin.dsl.module

object DataModule {

    val repositoryModules = module {
        single { HomeRepository() }

    }

}