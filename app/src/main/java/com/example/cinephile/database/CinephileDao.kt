package com.example.cinephile.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CinephileDao {

    //PopularMovies
    @Query("select * from databasemovieresultsitem")
    fun getPopularMovies(): LiveData<List<DatabaseMovieResultsItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg videos: DatabaseMovieResultsItem)

    //UpComingMovies
    @Query("select * from databaseupcomingmovieresultitem")
    fun getUpComingMovies(): LiveData<List<DatabaseUpComingMovieResultItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpComingMovies(vararg upComingMovies: DatabaseUpComingMovieResultItem)
}