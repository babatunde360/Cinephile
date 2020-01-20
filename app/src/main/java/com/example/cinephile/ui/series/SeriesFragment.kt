package com.example.cinephile.ui.series

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cinephile.databinding.FragmentSeriesBinding

class SeriesFragment : Fragment(){

    private val seriesViewModel by lazy {
        ViewModelProviders.of(this).get(SeriesViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSeriesBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewmodel = seriesViewModel
        binding.seriesList.adapter = SeriesAdapter()


        val layoutManager = GridLayoutManager(activity,2)
        binding.seriesList.layoutManager = layoutManager


        return binding.root
    }
}