package com.grupo7.brasilflixapp.di

import com.grupo7.brasilflixapp.di.DataModule.repositoryModules
import com.grupo7.brasilflixapp.di.DomainModule.useCaseModules
import com.grupo7.brasilflixapp.di.PresentationModule.viewModelModules
import org.koin.core.module.Module

object AppComponent {

    val allModules: List<Module> get() =
        listOf(
            *presentationModules,
            *dataModules,
            *domainModules
        )

    private val presentationModules: Array<Module> get() = arrayOf(viewModelModules)
    private val dataModules: Array<Module> get() = arrayOf(repositoryModules)
    private val domainModules: Array<Module> get() = arrayOf(useCaseModules)

}