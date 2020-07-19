package com.example.cinephile.ui.moviesdetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cinephile.domain.MovieResultsItem
import com.example.cinephile.network.CastItem
import com.example.cinephile.network.MovieApi
import com.example.cinephile.network.MovieVideoResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class MovieDetailViewModel(movieResultsItem: MovieResultsItem, app: Application) : AndroidViewModel(app) {

    private val coroutineScope = CoroutineScope(Job() + Dispatchers.Main)

    private val _selectedProperty = MutableLiveData<MovieResultsItem>()
    val selectedProperty: LiveData<MovieResultsItem>
        get() = _selectedProperty

    private val _movieVideo = MutableLiveData<List<MovieVideoResult>>()
    val movieVideo : LiveData<List<MovieVideoResult>>
    get() = _movieVideo

    private val _movieCastList = MutableLiveData<List<CastItem>>()
    val movieCastList: LiveData<List<CastItem>>
    get() = _movieCastList

    private val movieResultItemId: Int? = movieResultsItem.id
    init {
        _selectedProperty.value = movieResultsItem
        movieResultItemId?.let {
            getMovieCast(it)
            getMovieVideos(it)
        }

    }

    private fun getMovieVideos(movieResultItemId: Int){
        coroutineScope.launch(Dispatchers.Main) {
            val getMovieVideosDeferred = MovieApi.retrofitService
                .getMovieTrailerAsync(movieResultItemId)
            try {
                val movieVideoResult = getMovieVideosDeferred.await()
                _movieVideo.value = movieVideoResult.results
            }catch (e: java.lang.Exception){
                Timber.d(e)
            }
        }
    }

    private fun getMovieCast(movieResultItemId: Int){
        coroutineScope.launch(Dispatchers.Main) {
            val getMovieCastDeferred = MovieApi.retrofitService
                .getMovieCastAsync(movieResultItemId)
            try{
                val movieCastResult = getMovieCastDeferred.await()
                _movieCastList.value = movieCastResult.cast as List<CastItem>
            }catch (e: Exception){
                Timber.d(e)
            }

        }
    }

}
