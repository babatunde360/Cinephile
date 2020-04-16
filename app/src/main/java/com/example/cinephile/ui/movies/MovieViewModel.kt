package com.example.cinephile.ui.movies

import android.app.Application
import androidx.lifecycle.*
import com.example.cinephile.database.MovieItemResultDatabase.Companion.getDatabase
import com.example.cinephile.network.MovieApi
import com.example.cinephile.domain.MovieResultsItem
import com.example.cinephile.repository.CinephileRepository
import kotlinx.coroutines.*
import timber.log.Timber

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val viewModelJob = SupervisorJob()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = getDatabase(application)
    private val repository = CinephileRepository(database)

     val movieList = repository.popularMovies
    val UpComingMoviesList = repository.upComingMovies

    private val _navigateToSelectedProperty = MutableLiveData<MovieResultsItem>()
    val navigateToSelectedProperty : LiveData<MovieResultsItem>
    get() = _navigateToSelectedProperty

    init {
        coroutineScope.launch {
            repository.refreshPopularMovies()
            repository.refreshUpcomingMovies()
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
