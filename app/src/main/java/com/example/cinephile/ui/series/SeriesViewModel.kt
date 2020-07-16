package com.example.cinephile.ui.series

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cinephile.database.MovieItemResultDatabase
import com.example.cinephile.domain.SeriesResultsItem
import com.example.cinephile.repository.CinephileRepository
import com.example.cinephile.utils.isOnline
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SeriesViewModel(application: Application, context: Context?): AndroidViewModel(application){
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = MovieItemResultDatabase.getDatabase(application)
    private val repository = CinephileRepository(database)

    val topRatedList = repository.topRatedSeries
    val popularSeriesList = repository.popularSeries
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
                repository.refreshPopularSeries()
                repository.refreshTopRatedSeries()
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