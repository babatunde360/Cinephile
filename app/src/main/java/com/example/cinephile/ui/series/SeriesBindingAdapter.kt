package com.example.cinephile.ui.series

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cinephile.network.SeriesResultsItem

@BindingAdapter("seriesListData")
fun bindSeriesRecyclerView(recyclerView: RecyclerView, data: List<SeriesResultsItem>?){
    val adapter = recyclerView.adapter as SeriesAdapter
    adapter.submitList(data)
}

@BindingAdapter("seriesTitle")
fun bindTitle(textView: TextView, title: String?){
    textView.text = title
}

@BindingAdapter("status")
fun bindStatus(textView: TextView, status: Int?){
    textView.text = status.toString()
}