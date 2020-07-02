package com.example.cinephile.ui.series

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cinephile.database.MovieItemResultDatabase
import com.example.cinephile.network.MovieApi
import com.example.cinephile.domain.SeriesResultsItem
import com.example.cinephile.repository.CinephileRepository
import com.example.cinephile.utils.isOnline
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class SeriesViewModel(application: Application, context: Context?): AndroidViewModel(application){
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

    @RequiresApi(Build.VERSION_CODES.M)
    val isConnected = isOnline(context)


    init {
        coroutineScope.launch {
            if(isConnected) {
                repository.deleteAiringToday()
                repository.refreshAiringToday()
            }
        }
    }

    private fun getPopularSeries() {
        coroutineScope.launch(Dispatchers.IO){
            val getPopularSeriesDeferred = MovieApi.retrofitService.getPopularSeriesAsync()
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
            val getDeferredTopRated = MovieApi.retrofitService.getTopRatedSeriesAsync()
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