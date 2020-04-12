package com.example.cinephile.ui.seriesdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cinephile.databinding.FragmentSeriesCastPagerBinding
import com.example.cinephile.ui.moviesdetail.CastFragmentAdapter


class SeriesCastFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSeriesCastPagerBinding.inflate(inflater)


        val viewModel: SeriesDetailViewModel? =  parentFragment?.let {
            ViewModelProvider(it)
                .get(SeriesDetailViewModel::class.java)
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        

        val manager = GridLayoutManager(activity,2)
        binding.castRecyclerView.layoutManager = manager
        binding.castRecyclerView.adapter = CastFragmentAdapter()

        return binding.root
    }
}