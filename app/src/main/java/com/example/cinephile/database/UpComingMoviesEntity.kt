package com.example.cinephile.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cinephile.domain.MovieResultsItem

@Entity
data class DatabaseUpComingMovieResultItem constructor(
    val popularity: String? = null,
    @PrimaryKey
    val id: Int?,
    val video: Boolean?,
    val voteCount: Int?,
    val voteAverage: String?,
    val title: String?,
    val releaseDate: String?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val backdropPath: String?,
    val adult: Boolean?,
    val overview: String?,
    val posterPath: String?
)


fun List<DatabaseUpComingMovieResultItem>.asDomainModel():List<MovieResultsItem>{
    return map {
        MovieResultsItem(
            popularity = it.popularity,
            id = it.id,
            video = it.video,
            voteCount = it.voteCount,
            voteAverage = it.voteAverage,
            title = it.title,
            releaseDate = it.releaseDate,
            originalLanguage = it.originalLanguage,
            originalTitle = it.originalTitle,
            backdropPath = it.backdropPath,
            adult = it.adult,
            overview = it.overview,
            posterPath = it.posterPath
        )
    }
}