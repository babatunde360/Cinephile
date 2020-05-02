package com.example.cinephile.ui.series

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinephile.database.MovieItemResultDatabase
import com.example.cinephile.network.MovieApi
import com.example.cinephile.domain.SeriesResultsItem
import com.example.cinephile.repository.CinephileRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class SeriesViewModel(application: Application): AndroidViewModel(application){
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = MovieItemResultDatabase.getDatabase(application)
    private val repository = CinephileRepository(database)


    private val _topRatedList = MutableLiveData<List<SeriesResultsItem>>()
    val topRatedList: LiveData<List<SeriesResultsItem>>
        get() = _topRatedList

    private val _popularSeriesList = MutableLiveData<List<SeriesResultsItem>>()
    val popularSeriesList: LiveData<List<SeriesResultsItem>>
    get() = _popularSeriesList

    val airingTodayList = repository.airingToday

    private val _navigateToSelectedProperty = MutableLiveData<SeriesResultsItem>()
    val navigateToSelectedProperty : LiveData<SeriesResultsItem>
        get() = _navigateToSelectedProperty


    init {
        coroutineScope.launch {
            repository.refreshAiringToday()
        }
    }

    private fun getPopularSeries() {
        coroutineScope.launch(Dispatchers.IO){
            val getPopularSeriesDeferred = MovieApi.retrofitService.getPopularSeries()
            try {
                val popularSeriesResult = getPopularSeriesDeferred.await()
                _popularSeriesList.value = popularSeriesResult.results as List<SeriesResultsItem>?
            }catch (e: Exception){
                Timber.d(e)
            }
        }
    }

    private fun getTopRatedSeries() {
        coroutineScope.launch(Dispatchers.IO) {
            val getDeferredTopRated = MovieApi.retrofitService.getTopRatedSeries()
            try{
                val seriesListResult = getDeferredTopRated.await()
                _topRatedList.value = seriesListResult.results as List<SeriesResultsItem>?


            }catch (e:Exception){
                Timber.d(e)
            }
        }
    }



    fun displayPropertyDetails(itemSeries: SeriesResultsItem){
        _navigateToSelectedProperty.value = itemSeries
    }
    fun displayPropertyDetailsComplete(){
        _navigateToSelectedProperty.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}