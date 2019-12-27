package com.example.cinephile.ui.moviesdetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cinephile.network.ResultsItem

class MovieDetailViewModel(resultsItem: ResultsItem, app: Application) : AndroidViewModel(app) {

    private val _selectedProperty = MutableLiveData<ResultsItem>()
    val selectedProperty: LiveData<ResultsItem>
        get() {
            return _selectedProperty
        }

    init {
        _selectedProperty.value = resultsItem
    }
}
