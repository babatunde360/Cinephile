package com.example.cinephile.ui.seriesdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinephile.databinding.SeriesSeasonFragmentPagerBinding


class SeriesSeasonFragment : Fragment() {

    companion object {
        fun newInstance() = SeriesSeasonFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = SeriesSeasonFragmentPagerBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val viewModel: SeriesDetailViewModel? = parentFragment?.let {
            ViewModelProvider(it)
                .get(SeriesDetailViewModel::class.java)
        }
        binding.viewModel = viewModel
        binding.seriesSeasonRecyclerView.apply {
            adapter = SeriesSeasonAdapter()
            layoutManager = LinearLayoutManager(context)
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
