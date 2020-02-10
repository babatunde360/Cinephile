package com.example.cinephile.network

import com.squareup.moshi.Json

data class UpcomingMovies(

	@Json(name="results")
	val results: List<MovieResultsItem?>? = null,

	@Json(name="page")
	val page: Int? = null,

	@Json(name="total_results")
	val totalResults: Int? = null,

	@Json(name="dates")
	val upComingMoviesDates: UpComingMoviesDates? = null,

	@Json(name="total_pages")
	val totalPages: Int? = null
)