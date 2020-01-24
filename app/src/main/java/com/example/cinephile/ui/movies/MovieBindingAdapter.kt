package com.example.cinephile.ui.movies

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.cinephile.R
import com.example.cinephile.network.CastItem
import com.example.cinephile.network.MovieResultsItem
import com.example.cinephile.ui.moviesdetail.CastFragmentAdapter


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MovieResultsItem>?){
    val adapter = recyclerView.adapter as MovieAdapter
    adapter.submitList(data)
}

@BindingAdapter("movieImage")
fun bindImage(imageView: ImageView, imageUrl: String?){
    imageUrl?.let{
        val url = "https://image.tmdb.org/t/p/original$imageUrl"
        Glide.with(imageView.context)
            .load(url)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
            .into(imageView)


    }
}@BindingAdapter("movieDetailImage")
fun bindDetailImage(imageView: ImageView, imageUrl: String?){
    imageUrl?.let{
        val url = "https://image.tmdb.org/t/p/original$imageUrl"
        Glide.with(imageView.context)
            .load(url)
            .apply(
                RequestOptions().override(600,350)
                .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
            .into(imageView)


    }
}
@BindingAdapter("movieListData")
fun bindCastRecyclerView(recyclerView: RecyclerView, data: List<CastItem>?){
    val adapter = recyclerView.adapter as CastFragmentAdapter
    adapter.submitList(data)
}

@BindingAdapter("movieTitle")
fun bindTitle(textView: TextView, title: String?){
    textView.text = title
}
