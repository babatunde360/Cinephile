package com.example.cinephile.ui.seriesdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.cinephile.databinding.SeriesDetailFragmentBinding
import com.example.cinephile.ui.series.SeriesDetailViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.series_detail_fragment.*

class SeriesDetailFragment : Fragment() {

    private lateinit var binding: SeriesDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        binding = SeriesDetailFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val seriesResultItem = arguments?.
            let { SeriesDetailFragmentArgs.fromBundle(it).selectedSeries }

        val viewModels:SeriesDetailViewModel by viewModels{ SeriesDetailViewModelFactory(
            seriesResultItem,application) }

        binding.seriesDetailviewPager.adapter = SeriesDetailViewPagerAdapter(this)

        binding.seriesDetailViewModel = viewModels

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        NavigationUI.setupWithNavController(series_detail_toolbar,findNavController())
        val seriesDetailTabs = binding.seriesDetailTabs
        val seriesDetailViewPager = binding.seriesDetailviewPager

        TabLayoutMediator(seriesDetailTabs,seriesDetailViewPager){tab, position ->
            when(position){
                0->{
                    tab.text = "Info"
                }else->{
                tab.text = "Seasons"
            }
            }

        }.attach()
    }

}
