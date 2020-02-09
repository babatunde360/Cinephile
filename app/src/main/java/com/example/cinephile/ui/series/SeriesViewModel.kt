package com.example.cinephile.ui.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinephile.network.MovieApi
import com.example.cinephile.network.SeriesResultsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class SeriesViewModel: ViewModel(){
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _topRatedList = MutableLiveData<List<SeriesResultsItem>>()
    val topRatedList: LiveData<List<SeriesResultsItem>>
        get() = _topRatedList

    private val _popularSeriesList = MutableLiveData<List<SeriesResultsItem>>()
    val popularSeriesList: LiveData<List<SeriesResultsItem>>
    get() = _popularSeriesList

    private val _airingTodayList = MutableLiveData<List<SeriesResultsItem>>()
    val airingTodayList : LiveData<List<SeriesResultsItem>>
    get() = _airingTodayList

    private val _navigateToSelectedProperty = MutableLiveData<SeriesResultsItem>()
    val navigateToSelectedProperty : LiveData<SeriesResultsItem>
        get() = _navigateToSelectedProperty

    init {
        getTopRatedSeries()
        getAiringToday()
        getPopularSeries()
    }

    private fun getPopularSeries() {
        coroutineScope.launch {
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
        coroutineScope.launch {
            val getDeferredTopRated = MovieApi.retrofitService.getTopRatedSeries()
            try{
                val seriesListResult = getDeferredTopRated.await()
                _topRatedList.value = seriesListResult.results as List<SeriesResultsItem>?


            }catch (e:Exception){
                Timber.d(e)
            }
        }
    }

    private fun getAiringToday(){
        coroutineScope.launch {
            val getAiringTodayDeferred = MovieApi.retrofitService.getMovieAiringToday()
            try{
                val airingTodayResult = getAiringTodayDeferred.await()
                _airingTodayList.value = airingTodayResult.results as List<SeriesResultsItem>?
            }catch (e: Exception){
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