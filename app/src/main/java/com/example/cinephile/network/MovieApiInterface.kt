package com.example.cinephile.network

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
            Deferred<Movie>
}