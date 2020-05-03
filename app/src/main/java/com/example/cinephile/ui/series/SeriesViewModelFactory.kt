package com.example.cinephile.ui.series

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SeriesViewModelFactory(
    private val application: Application,
    private val context: Context?
    ): ViewModelProvider.Factory{
    @Suppress("uncecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SeriesViewModel::class.java)){
            return SeriesViewModel(application,context) as T
        }
        throw IllegalArgumentException("Unknown viewModel class")
    }
}
