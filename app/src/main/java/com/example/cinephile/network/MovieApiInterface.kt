package com.example.cinephile.network

import com.example.cinephile.domain.AiringToday
import com.example.cinephile.ui.series.PopularSeries
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

private const val api_key= PrivateApiKey

interface MovieApiInterface {
    @GET("movie/popular?api_key=${api_key}")
    fun getLatestMovies():
            Deferred<NetworkPopularMovies>

    @GET("movie/upcoming?api_key=${api_key}&language=en-US&region=us")
    fun getUpComingMovies():
            Deferred<NetworkUpComingMovies>


    @GET("tv/top_rated?api_key=${api_key}")
    fun getLatestSeries():
            Deferred<Series>

    @GET("movie/{movieId}/credits?api_key=${api_key}")
    fun getMovieCast(@Path("movieId") movieId: Int?):
            Deferred<MovieCredits>


    @GET("/3/tv/{seriesId}?api_key=${api_key}&language=en-US")
    fun getSeriesDetails(@Path("seriesId") seriesId: Int?):
            Deferred<SeriesDetails>

    @GET("tv/{seriesId}/credits?api_key=${api_key}&language=en-US")
    fun getSeriesCast(@Path("seriesId") seriesId: Int?):
            Deferred<SeriesCredits>

    @GET("tv/airing_today?api_key=${api_key}&language=en-US&timezone=gmt+1")
    fun getSeriesAiringToday():
            Deferred<NetworkAiringToday>

    @GET("tv/top_rated?api_key=${api_key}&language=en-US")
    fun getTopRatedSeries():
            Deferred<TopRated>

    @GET("tv/popular?api_key=${api_key}&language=en-US")
    fun getPopularSeries():
            Deferred<PopularSeries>


}
