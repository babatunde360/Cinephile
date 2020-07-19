package com.example.cinephile.ui.series

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cinephile.databinding.SeriesViewItemBinding
import com.example.cinephile.domain.SeriesResultsItem

class SeriesPagingAdapter(private val onClickListener: OnClickListener)
    : PagedListAdapter<SeriesResultsItem, SeriesPagingAdapter.SeriesViewHolder>(DiffCallback) {


    class SeriesViewHolder(private var binding: SeriesViewItemBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(series: SeriesResultsItem){
            binding.currentSeries = series
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
        if (currentSeries != null) {
            holder.bind(currentSeries)
            holder.itemView.setOnClickListener{
                onClickListener.clickListener(currentSeries)
            }
        }

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
    class OnClickListener(val clickListener: (itemSeries: SeriesResultsItem) -> Unit){
        fun onClick(itemSeries: SeriesResultsItem) = clickListener(itemSeries)
    }
}