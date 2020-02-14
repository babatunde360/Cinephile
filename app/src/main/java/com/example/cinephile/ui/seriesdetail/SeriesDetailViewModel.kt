package com.example.cinephile.ui.seriesdetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cinephile.network.CastItem
import com.example.cinephile.network.MovieApi
import com.example.cinephile.network.SeriesResultsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class SeriesDetailViewModel(seriesResultItem : SeriesResultsItem, app: Application)
    : AndroidViewModel(app) {

private val job = Job()
    private val coroutineScope = CoroutineScope(job + Dispatchers.Main)

    private val _selectedProperty = MutableLiveData<SeriesResultsItem>()
    val selectedProperty: LiveData<SeriesResultsItem>
        get() = _selectedProperty

    private val _seriesCastList = MutableLiveData<List<CastItem>>()
    val seriesCastList: LiveData<List<CastItem>>
        get() = _seriesCastList



    val seriesResultsItemId = seriesResultItem.id
    init {
        _selectedProperty.value = seriesResultItem
        seriesResultsItemId?.let { getSeriesCast(it) }

    }
    private fun getSeriesCast(seriesResultItemId: Int){
        coroutineScope.launch {
            val getSeriesCastDeferred = MovieApi.retrofitService.getSeriesCast(seriesResultItemId)
            try{
                val seriesCastResult = getSeriesCastDeferred.await()
                _seriesCastList.value = seriesCastResult.cast as List<CastItem>
            }catch (e: Exception){
                Timber.d(e)
            }
        }
    }

}
