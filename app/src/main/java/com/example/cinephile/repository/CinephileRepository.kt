package com.example.cinephile.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.cinephile.database.MovieItemResultDatabase
import com.example.cinephile.database.asDomainModel
import com.example.cinephile.network.asDatabaseModel
import com.example.cinephile.domain.MovieResultsItem
import com.example.cinephile.domain.SeriesResultsItem
import com.example.cinephile.network.MovieApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class CinephileRepository(private val database: MovieItemResultDatabase){

    //PopularMovies
    suspend fun refreshPopularMovies(){
        withContext(Dispatchers.IO){
            val popularMovies =
                MovieApi.retrofitService.getLatestMovies().await()
            database.cinephileDao().insertAll(*popularMovies.asDatabaseModel())
        }
    }

    val popularMovies: LiveData<List<MovieResultsItem>> =
        Transformations.map(database.cinephileDao().getPopularMovies()){
            it.asDomainModel()
        }


    //UpcomingMovies
    suspend fun refreshUpcomingMovies() {
        withContext(Dispatchers.IO) {
            val upComingMovies =
                MovieApi.retrofitService.getUpComingMovies().await()
            database.cinephileDao().insertUpComingMovies(*upComingMovies.asDatabaseModel())
        }
    }
        val upComingMovies:LiveData<List<MovieResultsItem>> =
            Transformations.map(database.cinephileDao().getUpComingMovies()){
                it.asDomainModel()
            }

    //Series AiringToday
    suspend fun refreshAiringToday(){
        withContext(Dispatchers.IO){
            val airingToday =
                MovieApi.retrofitService.getSeriesAiringToday().await()
            database.cinephileDao().insertAiringToday(*airingToday.asDatabaseModel())

        }
    }
    val airingToday:LiveData<List<SeriesResultsItem>> =
        Transformations.map(database.cinephileDao().getAiringTodaySeries()){
            it.asDomainModel()
        }

    suspend fun deleteAiringToday(){
        withContext(Dispatchers.IO){
            database.cinephileDao().deleteairingtoday()
        }
    }
}