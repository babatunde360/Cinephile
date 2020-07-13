package com.example.cinephile.ui.seriesdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinephile.databinding.FragmentSeriesCastPagerBinding
import com.example.cinephile.ui.moviesdetail.CastAdapter
import com.example.cinephile.utils.CastMarginItemDecoration


class SeriesCastFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSeriesCastPagerBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val viewModel: SeriesDetailViewModel? =  parentFragment?.let {
            ViewModelProvider(it)
                .get(SeriesDetailViewModel::class.java)
        }

        binding.viewModel = viewModel

        binding.castRecyclerView.apply {
            layoutManager =  LinearLayoutManager(activity,RecyclerView.HORIZONTAL,false)
            addItemDecoration(CastMarginItemDecoration(16))
           adapter = CastAdapter()
        }

        return binding.root
    }
}