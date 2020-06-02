package com.example.cinephile.network


import com.squareup.moshi.Json

data class SeriesGenres(

	@Json(name="id")
	val id: Int? = null,

	@Json(name="name")
	val name: String? = null
)