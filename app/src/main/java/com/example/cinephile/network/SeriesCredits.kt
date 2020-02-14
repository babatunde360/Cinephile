package com.example.cinephile.network

import com.squareup.moshi.Json

data class SeriesCredits(

	@Json(name="cast")
	val cast: List<CastItem?>? = null,

	@Json(name="crew")
	val crew: List<CrewItem?>? = null,

	@Json(name="id")
	val id: Int? = null
)