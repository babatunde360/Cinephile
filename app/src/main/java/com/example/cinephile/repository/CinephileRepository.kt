package com.example.cinephile.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.cinephile.database.MovieItemResultDatabase
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
    suspend fun refreshPopularMovies() {
        withContext(Dispatchers.IO) {
            val popularMovies =
                MovieApi.retrofitService.getPopularMoviesAsync().await()
            database.cinephileDao().insertPopularMovies(*popularMovies.asDatabaseModel())
        }
    }
    private val popularMoviesFactory =
        database.cinephileDao().getPopularMovies()

    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(INITIAL_LOAD_SIZE_HINT)
        .setPageSize(PAGE_SIZE)
        .build()

    val popularMoviesData =
        LivePagedListBuilder(popularMoviesFactory,pagedListConfig).build()



    //UpcomingMovies
    suspend fun refreshUpcomingMovies() {
        withContext(Dispatchers.IO) {
            val upComingMovies =
                MovieApi.retrofitService.getUpComingMoviesAsync().await()
            database.cinephileDao().insertUpComingMovies(*upComingMovies.asDatabaseModel())
        }
    }
    private val upComingMoviesFactory =
        database.cinephileDao().getUpComingMovies()

    val upComingMovies  =
        LivePagedListBuilder(upComingMoviesFactory,pagedListConfig).build()

    //Series AiringToday
    suspend fun refreshAiringToday(){
        withContext(Dispatchers.IO){
            val airingToday =
                MovieApi.retrofitService.getSeriesAiringTodayAsync().await()
            database.cinephileDao().insertAiringToday(*airingToday.asDatabaseModel())

        }
    }

    private val airingTodayFactory =
        database.cinephileDao().getAiringTodaySeries()

    val airingToday:LiveData<PagedList<SeriesResultsItem>> =
        LivePagedListBuilder(airingTodayFactory,pagedListConfig).build()

    suspend fun deleteAiringToday(){
        withContext(Dispatchers.IO){
            database.cinephileDao().deleteairingtoday()
        }
    }

    //Popular Series
    suspend fun refreshPopularSeries(){
        withContext(Dispatchers.IO){
            val popularSeries =
                MovieApi.retrofitService.getPopularSeriesAsync().await()
            database.cinephileDao().insertPopularSeries(*popularSeries.asDatabaseModel())
        }
    }
    private val popularSeriesFactory =
        database.cinephileDao().getPopularSeries()

    val popularSeries:LiveData<PagedList<SeriesResultsItem>> =
        LivePagedListBuilder(popularSeriesFactory,pagedListConfig).build()

    //TopRated Series
    suspend fun refreshTopRatedSeries(){
        withContext(Dispatchers.IO){
            val topRatedSeries =
                MovieApi.retrofitService.getTopRatedSeriesAsync().await()
            database.cinephileDao().insertTopRatedSeries(*topRatedSeries.asDatabaseModel())
        }
    }

    private val topRatedSeriesFactory = database.cinephileDao().getTopRatedSeries()

    val topRatedSeries =
        LivePagedListBuilder(topRatedSeriesFactory,pagedListConfig).build()

}