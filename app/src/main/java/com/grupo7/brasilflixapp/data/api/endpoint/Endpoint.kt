package com.grupo7.brasilflixapp.data.api.endpoint

import com.grupo7.brasilflixapp.ui.model.films.films
import com.grupo7.brasilflixapp.ui.model.films.filmsResults
import com.grupo7.brasilflixapp.ui.model.reviews.ReviewResults
import com.grupo7.brasilflixapp.ui.model.series.Series
import com.grupo7.brasilflixapp.ui.model.series.SeriesResults
import com.grupo7.brasilflixapp.ui.model.videos.Videos
import com.grupo7.brasilflixapp.ui.model.videos.VideosResults
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

    @GET("tv/popular")
    suspend fun getSeriesPopular(@Query("page") page: Int ) : Response<SeriesResults>

    @GET("tv/top_rated")
    suspend fun getSeriesTopRated(@Query("page") page: Int ) : Response<SeriesResults>

    @GET("movie/upcoming")
    suspend fun getUpComing(@Query("page") page: Int ) : Response<filmsResults>

    @GET("search/movie")
    suspend fun searchMovies(@Query("query") search: String) : Response<filmsResults>

    @GET("search/tv")
    suspend fun searchSeries(@Query("query") search: String) : Response<SeriesResults>

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

    @GET("movie/{movie_id}/videos")
    suspend fun getMoviesVideos(
        @Path("movie_id") movieId: Int): Response<VideosResults>

    @GET("tv/{tv_id}/videos")
    suspend fun getSeriesVideos(
        @Path("tv_id") serieId: Int): Response<VideosResults>


}