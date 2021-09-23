package com.grupo7.brasilflixapp.api.main

import com.grupo7.brasilflixapp.model.films.films
import com.grupo7.brasilflixapp.model.films.filmsResults
import com.grupo7.brasilflixapp.model.reviews.ReviewResults
import com.grupo7.brasilflixapp.model.series.Series
import com.grupo7.brasilflixapp.model.series.SeriesResults
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Endpoint {
    @GET("movie/top_rated")
    suspend fun getFilmes(@Query("page") page: Int ) : Response<filmsResults>

    @GET("movie/popular")
    suspend fun getPopular(@Query("page") page: Int ) : Response<filmsResults>

    @GET("tv/on_the_air")
    suspend fun getSeries(@Query("page") page: Int ) : Response<SeriesResults>

    @GET("tv/top_rated")
    suspend fun getSeriesTopRated(@Query("page") page: Int ) : Response<SeriesResults>

    @GET("movie/upcoming")
    suspend fun getUpComing(@Query("page") page: Int ) : Response<filmsResults>

    @GET("search/movie")
    suspend fun searchMovies(@Query("page") page: Int, @Query("query") search: String) : Response<filmsResults>

    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movieId: Int
    ): Response<films>

    @GET("tv/{tv_id}")
    suspend fun getSeriesById(
        @Path("tv_id") serieId: Int
    ): Response<Series>

    @GET("movie/{movie_id}/reviews")
    suspend fun getReviewsMovies(
        @Path("movie_id") reviews: Int, @Query("page") page: Int
    ): Response<ReviewResults>


}