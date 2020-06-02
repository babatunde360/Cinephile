package com.example.cinephile.network

import com.squareup.moshi.Json

data class SeriesDetails(

	@Json(name="backdrop_path")
	val backdropPath: String? = null,

	@Json(name="created_by")
	val seriesCreatedBy: List<SeriesCreatedBy?>? = null,

	@Json(name="episode_run_time")
	val episodeRunTime: List<Int?>? = null,

	@Json(name="first_air_date")
	val firstAirDate: String? = null,

	@Json(name="genres")
	val genres: List<SeriesGenres?>? = null,

	@Json(name="homepage")
	val homepage: String? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="in_production")
	val inProduction: Boolean? = null,

	@Json(name="languages")
	val languages: List<String?>? = null,

	@Json(name="last_air_date")
	val lastAirDate: String? = null,

	@Json(name="last_episode_to_air")
	val seriesLastEpisodeToAir: SeriesLastEpisodeToAir? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="next_episode_to_air")
	val nextEpisodeToAir: SeriesNextEpisodeToAir? = null,

	@Json(name="networks")
	val networks: List<SeriesNetworks?>? = null,

	@Json(name="number_of_episodes")
	val numberOfEpisodes: Int? = null,

	@Json(name="number_of_seasons")
	val numberOfSeasons: Int? = null,

	@Json(name="origin_country")
	val originCountry: List<String?>? = null,

	@Json(name="original_language")
	val originalLanguage: String? = null,

	@Json(name="original_name")
	val originalName: String? = null,

	@Json(name="overview")
	val overview: String? = null,

	@Json(name="popularity")
	val popularity: Any? = null,

	@Json(name="poster_path")
	val posterPath: String? = null,

	@Json(name="production_companies")
	val seriesProductionCompanies: List<SeriesProductionCompanies?>? = null,

	@Json(name="seasons")
	val seasons: List<SeriesSeasons?>? = null,

	@Json(name="status")
	val status: String? = null,

	@Json(name="type")
	val type: String? = null,

	@Json(name="vote_average")
	val voteAverage: Any? = null,

	@Json(name="vote_count")
	val voteCount: Int? = null
)