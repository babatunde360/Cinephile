package com.example.cinephile.network

import com.example.cinephile.ui.series.PopularSeries
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

private const val api_key= PrivateApiKey

interface MovieApiInterface {
    @GET("movie/popular?api_key=${api_key}")
    fun getLatestMovies():
            Deferred<PopularMovies>

    @GET("tv/top_rated?api_key=${api_key}")
    fun getLatestSeries():
            Deferred<Series>

    @GET("movie/{movieId}/credits?api_key=${api_key}")
    fun getMovieCast(@Path("movieId") movieId: Int?):
            Deferred<MovieCredits>

    @GET("tv/{seriesId}/credits?api_key=${api_key}&language=en-US")
    fun getSeriesCast(@Path("seriesId") seriesId: Int?):
            Deferred<SeriesCredits>

    @GET("tv/airing_today?api_key=${api_key}&language=en-US")
    fun getMovieAiringToday():
            Deferred<AiringToday>

    @GET("tv/top_rated?api_key=${api_key}&language=en-US")
    fun getTopRatedSeries():
            Deferred<TopRated>

    @GET("tv/popular?api_key=${api_key}&language=en-US")
    fun getPopularSeries():
            Deferred<PopularSeries>

    @GET("movie/upcoming?api_key=${api_key}&language=en-US&region=us")
    fun getUpComingMovies():
            Deferred<UpcomingMovies>

}
