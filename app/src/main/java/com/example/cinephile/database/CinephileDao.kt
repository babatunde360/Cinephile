package com.example.cinephile.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cinephile.domain.MovieResultsItem
import com.example.cinephile.domain.SeriesResultsItem

@Dao
interface CinephileDao {

    //PopularMovies
    @Query("select * from databasemovieresultsitem")
    fun getPopularMovies(): DataSource.Factory<Int, MovieResultsItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularMovies(vararg videos: DatabaseMovieResultsItem)

    //UpComingMovies
    @Query("select * from databaseupcomingmovieresultitem")
    fun getUpComingMovies(): DataSource.Factory<Int, MovieResultsItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpComingMovies(vararg upComingMovies: DatabaseUpComingMovieResultItem)

    //Series AiringToday
    @Query("select * from databaseairingtodayseriesitem")
    fun getAiringTodaySeries(): DataSource.Factory<Int,SeriesResultsItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAiringToday(vararg airingToday: DatabaseAiringTodaySeriesItem)

    @Query("delete from databaseairingtodayseriesitem")
    fun deleteairingtoday()

    //PopularSeries
    @Query("select * from databasepopularseriesitem")
    fun getPopularSeries():LiveData<List<DatabasePopularSeriesItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularSeries(vararg popularSeries: DatabasePopularSeriesItem)

    @Query("select * from databasetopratedseriesitem")
    fun getTopRatedSeries():LiveData<List<DatabaseTopRatedSeriesItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopRatedSeries(vararg topRatedSeries: DatabaseTopRatedSeriesItem)

}