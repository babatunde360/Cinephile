package com.example.cinephile.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.cinephile.database.MovieItemResultDatabase
import com.example.cinephile.database.asDomainModel
import com.example.cinephile.network.asDatabaseModel
import com.example.cinephile.domain.MovieResultsItem
import com.example.cinephile.network.MovieApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class CinephileRepository(private val database: MovieItemResultDatabase){

    val popularMovies: LiveData<List<MovieResultsItem>> =
        Transformations.map(database.cinephileDao().getPopularMovies()){
            it.asDomainModel()
        }

    suspend fun refreshPopularMovies(){
        withContext(Dispatchers.IO){
            val popularMovies =
                MovieApi.retrofitService.getLatestMovies().await()
            database.cinephileDao().insertAll(*popularMovies.asDatabaseModel())
            }
    }
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

}