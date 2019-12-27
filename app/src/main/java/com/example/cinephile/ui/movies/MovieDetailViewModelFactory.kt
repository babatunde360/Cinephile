package com.example.cinephile.ui.movies

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cinephile.network.MovieResultsItem
import com.example.cinephile.ui.moviesdetail.MovieDetailViewModel

class MovieDetailViewModelFactory(
    private val movieResultsItem: MovieResultsItem,
    private val application: Application) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
                return MovieDetailViewModel(movieResultsItem, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")

    }
}