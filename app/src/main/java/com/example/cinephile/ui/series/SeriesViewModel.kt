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

    private val _seriesList = MutableLiveData<List<SeriesResultsItem>>()
    val seriesList: LiveData<List<SeriesResultsItem>>
        get() = _seriesList

    private val _airingTodayList = MutableLiveData<List<SeriesResultsItem>>()
    val airingTodayList : LiveData<List<SeriesResultsItem>>
    get() = _airingTodayList

    init {
        getLatestSeries()
        getAiringToday()
    }

    private fun getLatestSeries() {
        coroutineScope.launch {
            val getDeferredSeries = MovieApi.retrofitService.getLatestSeries()
            try{
                val seriesListResult = getDeferredSeries.await()
                _seriesList.value = seriesListResult.results as List<SeriesResultsItem>?


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

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}