package com.example.cinephile.ui.moviesdetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cinephile.network.MovieResultsItem

class MovieDetailViewModel(movieResultsItem: MovieResultsItem, app: Application) : AndroidViewModel(app) {

    private val _selectedProperty = MutableLiveData<MovieResultsItem>()
    val selectedProperty: LiveData<MovieResultsItem>
        get() {
            return _selectedProperty
        }

    init {
        _selectedProperty.value = movieResultsItem
    }
}
