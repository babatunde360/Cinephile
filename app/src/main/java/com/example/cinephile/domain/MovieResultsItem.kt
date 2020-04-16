package com.example.cinephile.domain

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResultsItem(
	@Json(name="popularity")
	val popularity: String?,
	@Json(name="id")
	val id: Int?,
	@Json(name="video")
	val video: Boolean? ,
	@Json(name="vote_count")
	val voteCount: Int? ,
	@Json(name="vote_average")
	val voteAverage: String?,
	@Json(name="title")
	val title: String?,
	@Json(name="release_date")
	val releaseDate: String?,
	@Json(name="original_language")
	val originalLanguage: String?,
	@Json(name="original_title")
	val originalTitle: String?,
	@Json(name="backdrop_path")
	val backdropPath: String?,
	@Json(name="adult")
	val adult: Boolean?,
	@Json(name="overview")
	val overview: String?,
	@Json(name="poster_path")
	val posterPath: String?
): Parcelable