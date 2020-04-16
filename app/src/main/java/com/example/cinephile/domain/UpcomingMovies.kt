package com.example.cinephile.domain

import com.example.cinephile.network.UpComingMoviesDates
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpcomingMovies(
    @Json(name="results")
	val results: List<MovieResultsItem?>?,
    @Json(name="page")
	val page: Int?,
    @Json(name="total_results")
	val totalResults: Int?,
    @Json(name="dates")
	val upComingMoviesDates: UpComingMoviesDates?,
    @Json(name="total_pages")
	val totalPages: Int?
)