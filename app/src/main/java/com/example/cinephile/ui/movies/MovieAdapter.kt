package com.example.cinephile.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cinephile.databinding.MovieViewItemBinding
import com.example.cinephile.network.ResultsItem

class MovieAdapter(val onClickListener: OnClickListener): ListAdapter<ResultsItem, MovieAdapter.MovieViewHolder>(DiffCallback){


    class MovieViewHolder(private var binding: MovieViewItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: ResultsItem){
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


    companion object DiffCallback : DiffUtil.ItemCallback<ResultsItem>(){
        override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class OnClickListener(val clickListener: (item: ResultsItem) -> Unit){
        fun onClick(item: ResultsItem) = clickListener(item)
    }

}
