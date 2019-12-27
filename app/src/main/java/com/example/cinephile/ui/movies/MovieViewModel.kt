package com.example.cinephile.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinephile.network.MovieApi
import com.example.cinephile.network.MovieResultsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class MovieViewModel : ViewModel() {

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _movieList = MutableLiveData<List<MovieResultsItem>>()
    val movieList: LiveData<List<MovieResultsItem>>
    get() = _movieList

    private val _navigateToSelectedProperty = MutableLiveData<MovieResultsItem>()
    val navigateToSelectedProperty : LiveData<MovieResultsItem>
    get() = _navigateToSelectedProperty


    init {
        getLatestMovies()
    }
    private fun getLatestMovies(){
        coroutineScope.launch {
                val getMovieDeffered = MovieApi.retrofitService.getLatestMovies()
            try{
                val movieListResult = getMovieDeffered.await()
                _movieList.value = movieListResult.results as List<MovieResultsItem>?

            }catch (e: Exception){
               Timber.d(e)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displayPropertyDetails(itemMovie: MovieResultsItem){
        _navigateToSelectedProperty.value = itemMovie
    }
    fun displayPropertyDetailsComplete(){
        _navigateToSelectedProperty.value = null
    }

}
