package com.example.cinephile.ui.seriesdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinephile.databinding.SeriesSeasonFragmentPagerBinding
import com.example.cinephile.utils.MarginItemDecoration


class SeriesSeasonFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = SeriesSeasonFragmentPagerBinding
            .inflate(inflater,container,false)
        binding.lifecycleOwner = this
        val viewModel: SeriesDetailViewModel? = parentFragment?.let {
            ViewModelProvider(it)
                .get(SeriesDetailViewModel::class.java)
        }
        binding.viewModel = viewModel
        binding.seriesSeasonRecyclerView.apply {
            adapter = SeriesSeasonAdapter()
            addItemDecoration(MarginItemDecoration(16))
            layoutManager = LinearLayoutManager(context)
        }
        return binding.root
    }

}
