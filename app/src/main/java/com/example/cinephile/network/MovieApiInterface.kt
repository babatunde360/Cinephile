package com.example.cinephile.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

private const val api_key= "b92576ff2b0d0f5e6da96d46f8a7182a"

interface MovieApiInterface {
    @GET("movie/popular?api_key=${api_key}")
    fun getLatestMovies():
            Deferred<PopularMovies>
    @GET("tv/top_rated?api_key=${api_key}")
    fun getLatestSeries():
            Deferred<Series>
}
