package com.example.cinephile.network


import com.squareup.moshi.Json


data class MovieCredits(

	@Json(name="id")
	val id: Int? = null,

	@Json(name="cast")
	val cast: List<CastItem?>? = null,

	@Json(name="crew")
	val crew: List<CrewItem?>? = null
)