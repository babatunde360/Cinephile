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

    private val _airingTodayList = MutableLiveData<List<SeriesResultsItem>>()
    val airingTodayList : LiveData<List<SeriesResultsItem>>
    get() = _airingTodayList

    init {
        getTopRatedSeries()
        getAiringToday()
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

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}