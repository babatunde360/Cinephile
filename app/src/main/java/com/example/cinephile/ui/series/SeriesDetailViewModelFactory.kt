package com.example.cinephile.ui.series

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cinephile.domain.SeriesResultsItem
import com.example.cinephile.ui.seriesdetail.SeriesDetailViewModel

class SeriesDetailViewModelFactory (
    private val seriesResultsItem: SeriesResultsItem,
    private val application: Application): ViewModelProvider.Factory{
    @Suppress("uncecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SeriesDetailViewModel::class.java)){
            return SeriesDetailViewModel(seriesResultsItem,application) as T
        }
        throw IllegalArgumentException("Unknown viewModel class")
    }
}

