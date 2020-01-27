package com.example.cinephile.ui.series

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.cinephile.R
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

@BindingAdapter("seriesImage")
fun bindImage(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        val url = "https://image.tmdb.org/t/p/original$imageUrl"
        Glide.with(imageView.context)
            .load(url)
            .apply(
                RequestOptions().override(600,350)
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imageView)
    }
}