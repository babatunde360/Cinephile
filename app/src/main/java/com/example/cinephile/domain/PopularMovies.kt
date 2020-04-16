package com.example.cinephile.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PopularMovies(
	@Json(name="page")
	val page: Int,
	@field:Json(name="total_results")
	val totalResults: Int,
	@field:Json(name="total_pages")
	val totalPages: Int,
	@Json(name="results")
	val results: List<MovieResultsItem?>?
)