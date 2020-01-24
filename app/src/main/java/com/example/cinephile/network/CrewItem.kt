package com.example.cinephile.network

import com.squareup.moshi.Json

data class CrewItem(

	@Json(name="credit_id")
	val creditId: String? = null,

	@Json(name="department")
	val department: String? = null,

	@Json(name="gender")
	val gender: Int? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="job")
	val job: String? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="profile_path")
	val profilePath: String? = null
)