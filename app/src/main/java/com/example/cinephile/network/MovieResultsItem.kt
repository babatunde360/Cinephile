package com.example.cinephile.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResultsItem(

	@Json(name="popularity")
	val popularity: String? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="video")
	val video: Boolean? = null,

	@Json(name="vote_count")
	val voteCount: Int? = null,

	@Json(name="vote_average")
	val voteAverage: String? = null,

	@Json(name="title")
	val title: String? = null,

	@Json(name="release_date")
	val releaseDate: String? = null,

	@Json(name="original_language")
	val originalLanguage: String? = null,

	@Json(name="original_title")
	val originalTitle: String? = null,

	@Json(name="genre_ids")
	val genreIds: List<Int?>? = null,

	@Json(name="backdrop_path")
	val backdropPath: String? = null,

	@Json(name="adult")
	val adult: Boolean? = null,

	@Json(name="overview")
	val overview: String? = null,

	@Json(name="poster_path")
	val posterPath: String? = null
): Parcelable