package com.grupo7.brasilflixapp.data.api.endpoint.series

import com.grupo7.brasilflixapp.ui.model.reviews.ReviewResults
import com.grupo7.brasilflixapp.ui.model.series.Series
import com.grupo7.brasilflixapp.ui.model.series.SeriesResults
import com.grupo7.brasilflixapp.ui.model.videos.VideosResults
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EndpointSeries {

    @GET("tv/on_the_air")
    suspend fun getSeries(@Query("page") page: Int ) : Response<SeriesResults>

    @GET("tv/popular")
    suspend fun getSeriesPopular(@Query("page") page: Int ) : Response<SeriesResults>

    @GET("tv/top_rated")
    suspend fun getSeriesTopRated(@Query("page") page: Int ) : Response<SeriesResults>

    @GET("tv/{tv_id}")
    suspend fun getSeriesById(
        @Path("tv_id") serieId: Int
    ): Response<Series>

    @GET("tv/{tv_id}/reviews")
    suspend fun getReviewsSeries(
        @Path("tv_id") reviews: Int, @Query("page") page: Int
    ): Response<ReviewResults>

    @GET("tv/{tv_id}/videos")
    suspend fun getSeriesVideos(
        @Path("tv_id") serieId: Int): Response<VideosResults>
}