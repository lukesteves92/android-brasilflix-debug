package com.grupo7.brasilflixapp.data.api.endpoint.movies

import com.grupo7.brasilflixapp.ui.model.films.films
import com.grupo7.brasilflixapp.ui.model.films.filmsResults
import com.grupo7.brasilflixapp.ui.model.reviews.ReviewResults
import com.grupo7.brasilflixapp.ui.model.videos.VideosResults
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EndpointMovies {

    @GET("movie/{movie_id}/reviews")
    suspend fun getReviewsMovies(
        @Path("movie_id") reviews: Int, @Query("page") page: Int
    ): Response<ReviewResults>

    @GET("movie/{movie_id}/videos")
    suspend fun getMoviesVideos(
        @Path("movie_id") movieId: Int): Response<VideosResults>

    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movieId: Int
    ): Response<films>

    @GET("movie/top_rated")
    suspend fun getFilmes(@Query("page") page: Int ) : Response<filmsResults>

    @GET("movie/popular")
    suspend fun getPopular(@Query("page") page: Int ) : Response<filmsResults>

    @GET("movie/upcoming")
    suspend fun getUpComing(@Query("page") page: Int ) : Response<filmsResults>
}