package com.example.cinephile.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinephile.network.MovieApi
import com.example.cinephile.network.ResultsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class MovieViewModel : ViewModel() {

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _movieList = MutableLiveData<List<ResultsItem>>()
    val movieList: LiveData<List<ResultsItem>>
    get() = _movieList

    private val _navigateToSelectedProperty = MutableLiveData<ResultsItem>()
    val navigateToSelectedProperty : LiveData<ResultsItem>
    get() = _navigateToSelectedProperty


    init {
        getLatestMovies()
    }
    private fun getLatestMovies(){
        coroutineScope.launch {
                val getMovieDeffered = MovieApi.retrofitService.getLatestMovies()
            try{
                val movieListResult = getMovieDeffered.await()
                _movieList.value = movieListResult.results as List<ResultsItem>?

            }catch (e: Exception){
               Timber.d(e)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displayPropertyDetails(item: ResultsItem){
        _navigateToSelectedProperty.value = item
    }
    fun displayPropertyDetailsComplete(){
        _navigateToSelectedProperty.value = null
    }

}
