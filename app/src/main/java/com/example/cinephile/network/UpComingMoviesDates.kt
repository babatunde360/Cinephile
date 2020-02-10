package com.example.cinephile.network

import com.squareup.moshi.Json

data class UpComingMoviesDates(

	@Json(name="maximum")
	val maximum: String? = null,

	@Json(name="minimum")
	val minimum: String? = null
)