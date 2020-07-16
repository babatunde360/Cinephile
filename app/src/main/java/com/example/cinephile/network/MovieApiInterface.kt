package com.example.cinephile.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

private const val api_key= PrivateApiKey

interface MovieApiInterface {
    @GET("movie/popular?api_key=${api_key}")
    fun getPopularMoviesAsync():
            Deferred<NetworkPopularMovies>

    @GET("movie/upcoming?api_key=${api_key}&language=en-US&region=us")
    fun getUpComingMoviesAsync():
            Deferred<NetworkUpComingMovies>


    @GET("tv/top_rated?api_key=${api_key}")
    fun getLatestSeriesAsync():
            Deferred<Series>

    @GET("movie/{movieId}/credits?api_key=${api_key}")
    fun getMovieCastAsync(@Path("movieId") movieId: Int?):
            Deferred<MovieCredits>

    @GET("movie/{movieId}/videos?api_key=${api_key}&language=en-US")
    fun getMovieTrailerAsync(@Path("movieId") movieId: Int?):
            Deferred<MovieVideos>

    @GET("/3/tv/{seriesId}?api_key=${api_key}&language=en-US")
    fun getSeriesDetailsAsync(@Path("seriesId") seriesId: Int?):
            Deferred<SeriesDetails>

    @GET("tv/{seriesId}/credits?api_key=${api_key}&language=en-US")
    fun getSeriesCastAsync(@Path("seriesId") seriesId: Int?):
            Deferred<SeriesCredits>

    @GET("tv/airing_today?api_key=${api_key}&language=en-US&timezone=gmt+1")
    fun getSeriesAiringTodayAsync():
            Deferred<NetworkAiringToday>

    @GET("tv/top_rated?api_key=${api_key}&language=en-US")
    fun getTopRatedSeriesAsync():
            Deferred<NetworkTopRated>

    @GET("tv/popular?api_key=${api_key}&language=en-US")
    fun getPopularSeriesAsync():
            Deferred<NetworkPopularSeries>


}
