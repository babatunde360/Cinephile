package com.example.cinephile.ui.moviesdetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cinephile.network.CastItem
import com.example.cinephile.network.MovieApi
import com.example.cinephile.domain.MovieResultsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class MovieDetailViewModel(movieResultsItem: MovieResultsItem, app: Application) : AndroidViewModel(app) {

    private val job = Job()
    private val coroutineScope = CoroutineScope(job + Dispatchers.Main)

    private val _selectedProperty = MutableLiveData<MovieResultsItem>()
    val selectedProperty: LiveData<MovieResultsItem>
        get() = _selectedProperty

    private val _movieCastList = MutableLiveData<List<CastItem>>()
    val movieCastList: LiveData<List<CastItem>>
    get() = _movieCastList

    private val _movieListNo = MutableLiveData<String>()
    val movieListNo: LiveData<String>
        get() = _movieListNo


    val movieResultItemId: Int? = movieResultsItem.id
    init {
        _selectedProperty.value = movieResultsItem
        movieResultItemId?.let { getMovieCast(it) }

    }

    private fun getMovieCast(movieResultItemId: Int){
        coroutineScope.launch(Dispatchers.IO){
            val getCastDeferred = MovieApi.retrofitService.getMovieCast(movieResultItemId)
            try{
                val movieListResult = getCastDeferred.await()
                _movieCastList.value = movieListResult.cast as List<CastItem>?
                _movieListNo.value = movieListResult.cast?.size.toString()

            }catch (e: Exception){
                Timber.d(e)
            }
        }
    }
}
