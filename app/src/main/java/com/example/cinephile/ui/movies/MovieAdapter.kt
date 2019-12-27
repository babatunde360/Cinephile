package com.example.cinephile.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cinephile.databinding.MovieViewItemBinding
import com.example.cinephile.network.MovieResultsItem

class MovieAdapter(val onClickListener: OnClickListener): ListAdapter<MovieResultsItem, MovieAdapter.MovieViewHolder>(DiffCallback){


    class MovieViewHolder(private var binding: MovieViewItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieResultsItem){
            binding.resultItem = movie
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MovieViewItemBinding.inflate(layoutInflater)
        return MovieViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentMovie = getItem(position)
        holder.bind(currentMovie)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(currentMovie)
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<MovieResultsItem>(){
        override fun areItemsTheSame(oldItemMovie: MovieResultsItem, newItemMovie: MovieResultsItem): Boolean {
            return oldItemMovie === newItemMovie
        }

        override fun areContentsTheSame(oldItemMovie: MovieResultsItem, newItemMovie: MovieResultsItem): Boolean {
            return oldItemMovie.id == newItemMovie.id
        }

    }

    class OnClickListener(val clickListener: (itemMovie: MovieResultsItem) -> Unit){
        fun onClick(itemMovie: MovieResultsItem) = clickListener(itemMovie)
    }

}
