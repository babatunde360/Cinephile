package com.example.cinephile.network

import com.squareup.moshi.Json

data class SeriesCreatedBy(

	@Json(name="id")
	val id: Int? = null,

	@Json(name="credit_id")
	val creditId: String? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="gender")
	val gender: Int? = null,

	@Json(name="profile_path")
	val profilePath: Any? = null
)