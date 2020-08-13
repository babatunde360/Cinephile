package com.example.cinephile.ui.series

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class SeriesViewModelFactory(
    private val application: Application,
    private val context: Context?
    ): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SeriesViewModel::class.java)){
            return SeriesViewModel(application,context) as T
        }
        throw IllegalArgumentException("Unknown viewModel class")
    }
}
