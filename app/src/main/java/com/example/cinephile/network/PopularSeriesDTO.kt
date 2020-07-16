package com.example.cinephile.network

import com.example.cinephile.database.DatabasePopularSeriesItem
import com.example.cinephile.domain.SeriesResultsItem
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkPopularSeries(
    @Json(name="page")
    val page: Int?,
    @Json(name="total_results")
    val totalResults: Int?,
    @Json(name="total_pages")
    val totalPages: Int,
    @Json(name="results")
    val results: List<SeriesResultsItem?>
)

@JsonClass(generateAdapter = true)
data class PopularSeriesResultsItem(
    @Json(name="original_name")
    val originalName: String?,
    @Json(name="name")
    val name: String?,
    @Json(name="popularity")
    val popularity: Double?,
    @Json(name="vote_count")
    val voteCount: Int?,
    @Json(name="first_air_date")
    val firstAirDate: String?,
    @Json(name="backdrop_path")
    val backdropPath: String?,
    @Json(name="original_language")
    val originalLanguage: String?,
    @Json(name="id")
    val id: Int?,
    @Json(name="vote_average")
    val voteAverage: Double?,
    @Json(name="overview")
    val overview: String?,
    @Json(name="poster_path")
    val posterPath: String?
)

fun NetworkPopularSeries.asDomainModel():List<SeriesResultsItem> {
    return results.map {
        SeriesResultsItem(
            originalName = it?.originalName,
            name = it?.name,
            popularity = it?.popularity,
            voteCount = it?.voteCount,
            firstAirDate = it?.firstAirDate,
            backdropPath = it?.backdropPath,
            originalLanguage = it?.originalLanguage,
            id = it?.id,
            voteAverage = it?.voteAverage,
            overview = it?.overview,
            posterPath = it?.posterPath
        )
    }
}
fun NetworkPopularSeries.asDatabaseModel():Array<DatabasePopularSeriesItem> {
    return results.map {
        DatabasePopularSeriesItem(
            originalName = it?.originalName,
            name = it?.name,
            popularity = it?.popularity,
            voteCount = it?.voteCount,
            firstAirDate = it?.firstAirDate,
            backdropPath = it?.backdropPath,
            originalLanguage = it?.originalLanguage,
            id = it?.id,
            voteAverage = it?.voteAverage,
            overview = it?.overview,
            posterPath = it?.posterPath
        )
    }.toTypedArray()
}
