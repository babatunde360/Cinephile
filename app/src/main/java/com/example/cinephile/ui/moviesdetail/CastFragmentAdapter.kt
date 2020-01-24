package com.example.cinephile.ui.moviesdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cinephile.databinding.MovieCastViewItemBinding
import com.example.cinephile.network.CastItem

class CastFragmentAdapter : ListAdapter<CastItem,CastFragmentAdapter.CastViewHolder>(DiffCallback) {

    class CastViewHolder(private val binding: MovieCastViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cast : CastItem){
            binding.movieCast = cast
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MovieCastViewItemBinding.inflate(layoutInflater)
        return CastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val currentCastMember = getItem(position)
        holder.bind(currentCastMember)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<CastItem>(){
        override fun areItemsTheSame(oldItemCast: CastItem, newItemCast: CastItem): Boolean {
            return oldItemCast === newItemCast
        }

        override fun areContentsTheSame(oldItemCast: CastItem, newItemCast: CastItem): Boolean {
            return oldItemCast.id == newItemCast.id
        }

    }
}