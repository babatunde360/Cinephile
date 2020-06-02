package com.example.cinephile.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.cinephile.database.MovieItemResultDatabase
import com.example.cinephile.database.asDomainModel
import com.example.cinephile.domain.MovieResultsItem
import com.example.cinephile.domain.SeriesResultsItem
import com.example.cinephile.network.MovieApi
import com.example.cinephile.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CinephileRepository(private val database: MovieItemResultDatabase){

    companion object {
        private const val PAGE_SIZE = 10
        private const val INITIAL_LOAD_SIZE_HINT = 20
    }

    //PopularMovies
    suspend fun refreshPopularMovies(){
        withContext(Dispatchers.IO){
            val popularMovies =
                MovieApi.retrofitService.getLatestMovies().await()
            database.cinephileDao().insertAll(*popularMovies.asDatabaseModel())
        }
    }

    private val popularMoviesFactory =
        database.cinephileDao().getPopularMovies()

    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(INITIAL_LOAD_SIZE_HINT)
        .setPageSize(PAGE_SIZE)
        .build()

    val popularMoviesData =
        LivePagedListBuilder(popularMoviesFactory,pagedListConfig).build()



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