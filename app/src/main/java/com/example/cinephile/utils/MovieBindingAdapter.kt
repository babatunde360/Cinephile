package com.example.cinephile.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.cinephile.R
import com.example.cinephile.domain.MovieResultsItem
import com.example.cinephile.domain.SeriesResultsItem
import com.example.cinephile.network.CastItem
import com.example.cinephile.network.SeriesSeasons
import com.example.cinephile.ui.movies.MoviePagingAdapter
import com.example.cinephile.ui.moviesdetail.CastFragmentAdapter
import com.example.cinephile.ui.series.SeriesAdapter
import com.example.cinephile.ui.seriesdetail.SeriesSeasonAdapter

@BindingAdapter("pagedData")
fun bindRecyclerView(recyclerView: RecyclerView, data: PagedList<MovieResultsItem>?){
    val adapter = recyclerView.adapter as MoviePagingAdapter
    adapter.submitList(data)
}

@BindingAdapter("movieDetailImage")
fun bindDetailImage(imageView: ImageView, imageUrl: String?){
    imageUrl?.let{
        val url = "https://image.tmdb.org/t/p/original$imageUrl"
        Glide.with(imageView.context)
            .load(url)
            .apply(
                RequestOptions().override(500,250)
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
            .into(imageView)
    }
}
@BindingAdapter("loadImage")
fun bindImage(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        val url = "https://image.tmdb.org/t/p/original$imageUrl"
        Glide.with(imageView.context)
            .load(url)
            .apply(
                RequestOptions()
                    .timeout(30000)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imageView)
    }
}

@BindingAdapter("movieListData")
fun bindCastRecyclerView(recyclerView: RecyclerView, data: List<CastItem>?){
    val adapter = recyclerView.adapter as CastFragmentAdapter
    adapter.submitList(data)
}

@BindingAdapter("seriesListData")
fun bindSeriesRecyclerView(recyclerView: RecyclerView, data: List<SeriesResultsItem>?){
    val adapter = recyclerView.adapter as SeriesAdapter
    adapter.submitList(data)
}

@BindingAdapter("seriesSeason")
fun bindSeasonRecyclerView(rv:RecyclerView, data: List<SeriesSeasons>?){
    val adapter = rv.adapter as SeriesSeasonAdapter
    adapter.submitList(data)
}