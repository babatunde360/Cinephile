package com.example.cinephile.ui.series

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cinephile.databinding.SeriesViewItemBinding
import com.example.cinephile.network.SeriesResultsItem

class SeriesAdapter: ListAdapter<SeriesResultsItem, SeriesAdapter.SeriesViewHolder>(DiffCallback) {


    class SeriesViewHolder(private var binding: SeriesViewItemBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(series: SeriesResultsItem){
            binding.seriesResultItem = series
            binding.executePendingBindings()
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SeriesViewItemBinding.inflate(layoutInflater)
        return SeriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
       val currentSeries = getItem(position)
        holder.bind(currentSeries)

    }
    companion object DiffCallback: DiffUtil.ItemCallback<SeriesResultsItem>() {
        override fun areItemsTheSame(
            oldItem: SeriesResultsItem,
            newItem: SeriesResultsItem
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: SeriesResultsItem,
            newItem: SeriesResultsItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }
}