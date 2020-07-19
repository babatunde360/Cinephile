package com.example.cinephile.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cinephile.databinding.MovieViewItemBinding
import com.example.cinephile.domain.MovieResultsItem

class MoviePagingAdapter(private val onClickListener: OnClickListener) :
    PagedListAdapter<MovieResultsItem,MoviePagingAdapter.MovieViewHolder>(DIFF_CALLBACK){
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
        if (currentMovie != null) {
            holder.bind(currentMovie)
            holder.itemView.setOnClickListener {
                onClickListener.onClick(currentMovie)
            }
        }
    }


    companion object{
        private val DIFF_CALLBACK = object:
        DiffUtil.ItemCallback<MovieResultsItem>(){
            override fun areItemsTheSame(
                oldItem: MovieResultsItem,
                newItem: MovieResultsItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MovieResultsItem,
                newItem: MovieResultsItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
    class OnClickListener(val clickListener: (itemMovie: MovieResultsItem) -> Unit){
        fun onClick(itemMovie: MovieResultsItem) = clickListener(itemMovie)
   }
}