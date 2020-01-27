package com.example.cinephile.ui.series

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cinephile.databinding.FragmentAiringTodayBinding

class AiringTodayFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAiringTodayBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val seriesViewModel = ViewModelProviders.of(this)
            .get(SeriesViewModel::class.java)

        binding.seriesViewModel = seriesViewModel

        binding.airingTodayRecyclerView.adapter = SeriesAdapter()
        binding.airingTodayRecyclerView.layoutManager = GridLayoutManager(activity,2)



        return binding.root
    }
}
