package com.example.cinephile.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class AiringToday(
	@Json(name="page")
	val page: Int?,
	@Json(name="total_results")
	val totalResults: Int?,
	@Json(name="total_pages")
	val totalPages: Int?,
	@Json(name="results")
	val results: List<SeriesResultsItem?>?
)