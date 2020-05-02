package com.example.cinephile.ui.seriesdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cinephile.databinding.SeriesSeasonViewItemBinding
import com.example.cinephile.network.SeriesSeasons

class SeriesSeasonAdapter :
    ListAdapter<SeriesSeasons,SeriesSeasonAdapter.SeriesSeasonViewHolder>(DiffCallback){

    class SeriesSeasonViewHolder(var binding: SeriesSeasonViewItemBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(currentSeason: SeriesSeasons){
            binding.seriesItem = currentSeason
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesSeasonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SeriesSeasonViewItemBinding.inflate(layoutInflater)
        return SeriesSeasonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeriesSeasonViewHolder, position: Int) {
        val currentSeason = getItem(position)
        holder.bind(currentSeason)
    }
    companion object DiffCallback: DiffUtil.ItemCallback<SeriesSeasons>(){
        override fun areItemsTheSame(
            oldItem: SeriesSeasons,
            newItem: SeriesSeasons
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: SeriesSeasons,
            newItem: SeriesSeasons
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }
}