package com.example.cinephile.network

import com.squareup.moshi.Json


data class SeriesNetworks(

	@Json(name="name")
	val name: String? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="logo_path")
	val logoPath: String? = null,

	@Json(name="origin_country")
	val originCountry: String? = null
)