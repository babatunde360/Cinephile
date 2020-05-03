package com.example.cinephile.ui.movies

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class MovieViewModelFactory(
    private val application: Application ,
    private val context: Context?
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(application,context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}