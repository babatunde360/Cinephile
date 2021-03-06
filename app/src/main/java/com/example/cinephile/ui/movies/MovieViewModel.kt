package com.example.cinephile.ui.movies

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cinephile.database.MovieItemResultDatabase.Companion.getDatabase
import com.example.cinephile.domain.MovieResultsItem
import com.example.cinephile.repository.CinephileRepository
import com.example.cinephile.utils.isOnline
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MovieViewModel(application: Application, context: Context?) : AndroidViewModel(application) {

    private val viewModelJob = SupervisorJob()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = getDatabase(application)
    private val repository = CinephileRepository(database)

     val movieList = repository.popularMoviesData
    val upComingMoviesList = repository.upComingMovies

    private val _navigateToSelectedProperty = MutableLiveData<MovieResultsItem>()
    val navigateToSelectedProperty : LiveData<MovieResultsItem>
    get() = _navigateToSelectedProperty

    @RequiresApi(Build.VERSION_CODES.M)
    val isConnected = isOnline(context)

    init {
        coroutineScope.launch {
            if (isConnected){
            repository.refreshPopularMovies()
            repository.refreshUpcomingMovies()
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
