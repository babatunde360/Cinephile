package com.example.cinephile.network

import com.squareup.moshi.Json

data class SeriesLastEpisodeToAir(

	@Json(name="air_date")
	val airDate: String? = null,

	@Json(name="episode_number")
	val episodeNumber: Int? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="overview")
	val overview: String? = null,

	@Json(name="production_code")
	val productionCode: String? = null,

	@Json(name="season_number")
	val seasonNumber: Int? = null,

	@Json(name="show_id")
	val showId: Int? = null,

	@Json(name="still_path")
	val stillPath: String? = null,

	@Json(name="vote_average")
	val voteAverage: Int? = null,

	@Json(name="vote_count")
	val voteCount: Int? = null
)