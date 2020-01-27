package com.example.cinephile.ui.series

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cinephile.databinding.FragmentTopRatedBinding

class TopRatedFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val binding = FragmentTopRatedBinding.inflate(inflater)

        val viewModel = ViewModelProviders.of(this).get(SeriesViewModel::class.java)
        binding.lifecycleOwner = this

        binding.topRatedRecyclerView.adapter = SeriesAdapter()
        binding.topRatedRecyclerView.layoutManager = GridLayoutManager(activity,2)

        binding.viewModel = viewModel

        return binding.root
    }
}
