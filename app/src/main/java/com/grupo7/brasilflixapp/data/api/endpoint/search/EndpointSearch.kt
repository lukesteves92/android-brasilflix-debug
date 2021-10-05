package com.grupo7.brasilflixapp.data.api.endpoint.search

import com.grupo7.brasilflixapp.ui.model.films.filmsResults
import com.grupo7.brasilflixapp.ui.model.series.SeriesResults
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EndpointSearch {

    @GET("search/movie")
    suspend fun searchMovies(@Query("query") search: String) : Response<filmsResults>

    @GET("search/tv")
    suspend fun searchSeries(@Query("query") search: String) : Response<SeriesResults>
}