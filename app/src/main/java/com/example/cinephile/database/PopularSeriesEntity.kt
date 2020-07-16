package com.example.cinephile.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cinephile.domain.SeriesResultsItem

@Entity
data class DatabasePopularSeriesItem constructor(
    val originalName: String?,
    val name: String?,
    val popularity: Double?,
    val voteCount: Int?,
    val firstAirDate: String?,
    val backdropPath: String?,
    val originalLanguage: String?,
    @PrimaryKey
    val id: Int?,
    val voteAverage: Double?,
    val overview: String?,
    val posterPath: String?
)

fun List<DatabasePopularSeriesItem>.asDomainModel():List<SeriesResultsItem>{
    return map {
        SeriesResultsItem(
            originalName = it.originalName,
            name = it.name,
            popularity = it.popularity,
            voteCount = it.voteCount,
            firstAirDate = it.firstAirDate,
            backdropPath = it.backdropPath,
            originalLanguage = it.originalLanguage,
            id = it.id,
            voteAverage = it.voteAverage,
            overview = it.overview,
            posterPath = it.posterPath
        )
    }
}