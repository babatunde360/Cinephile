package com.example.cinephile.network

import com.example.cinephile.domain.SeriesResultsItem
import com.squareup.moshi.Json

data class TopRated(

	@Json(name="page")
	val page: Int? = null,

	@Json(name="total_results")
	val totalResults: Int? = null,

	@Json(name="total_pages")
	val totalPages: Int? = null,

	@Json(name="results")
	val results: List<SeriesResultsItem?>? = null
)