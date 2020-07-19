package com.example.cinephile.network

import com.example.cinephile.database.DatabaseUpComingMovieResultItem
import com.example.cinephile.domain.MovieResultsItem
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkUpComingMovies(
    @Json(name="results")
    val results: List<MovieResultsItem?>,
    @Json(name="page")
    val page: Int?,
    @Json(name="total_results")
    val totalResults: Int?,
    @Json(name="dates")
    val upComingMoviesDates: UpComingMoviesDates?,
    @Json(name="total_pages")
    val totalPages: Int?


)

@JsonClass(generateAdapter = true)
data class UpComingMovieResultsItem(
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
)

fun NetworkUpComingMovies.asDomainModel():List<MovieResultsItem>{
    return results.map{
        MovieResultsItem(
            popularity = it?.popularity,
            id = it?.id,
            video = it?.video,
            voteCount = it?.voteCount,
            voteAverage = it?.voteAverage,
            title = it?.title,
            releaseDate = it?.releaseDate,
            originalLanguage = it?.originalLanguage,
            originalTitle = it?.originalTitle,
            backdropPath = it?.backdropPath,
            adult = it?.adult,
            overview = it?.overview,
            posterPath = it?.posterPath
        )
    }
}

fun NetworkUpComingMovies.asDatabaseModel():Array<DatabaseUpComingMovieResultItem>{
    return results.map {
        DatabaseUpComingMovieResultItem(
            popularity = it?.popularity,
            id = it?.id,
            video = it?.video,
            voteCount = it?.voteCount,
            voteAverage = it?.voteAverage,
            title = it?.title,
            releaseDate = it?.releaseDate,
            originalLanguage = it?.originalLanguage,
            originalTitle = it?.originalTitle,
            backdropPath = it?.backdropPath,
            adult = it?.adult,
            overview = it?.overview,
            posterPath = it?.posterPath)
    }.toTypedArray()
}

