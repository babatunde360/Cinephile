package com.example.cinephile.network

import com.squareup.moshi.Json

data class CastItem(

	@Json(name="cast_id")
	val castId: Int? = null,

	@Json(name="character")
	val character: String? = null,

	@Json(name="credit_id")
	val creditId: String? = null,

	@Json(name="gender")
	val gender: Int? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="order")
	val order: Int? = null,

	@Json(name="profile_path")
	val profilePath: String? = null
)