package com.example.cinephile.ui.series

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cinephile.databinding.SeriesAiringTodayItemViewBinding
import com.example.cinephile.network.SeriesResultsItem

class AiringTodayAdapter : ListAdapter<SeriesResultsItem, AiringTodayAdapter.AiringTodayViewHolder>(DiffCallback){

    class AiringTodayViewHolder(private val binding: SeriesAiringTodayItemViewBinding):
            RecyclerView.ViewHolder(binding.root){
            fun bind(series: SeriesResultsItem){
                binding.currentSeries = series
                binding.executePendingBindings()
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AiringTodayViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SeriesAiringTodayItemViewBinding.inflate(layoutInflater)
        return AiringTodayViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AiringTodayViewHolder, position: Int) {
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