package com.example.cinephile.ui.series

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cinephile.databinding.FragmentSeriesBinding
import com.google.android.material.tabs.TabLayoutMediator

class SeriesFragment : Fragment(){

    lateinit var binding: FragmentSeriesBinding

    private val seriesViewModel by lazy {
        ViewModelProvider(this).get(SeriesViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = FragmentSeriesBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewmodel = seriesViewModel

        binding.seriesViewPager.adapter = SeriesViewPagerAdapter(this)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val seriesTab = binding.seriesTab
        val seriesViewPager = binding.seriesViewPager
        TabLayoutMediator(seriesTab,seriesViewPager){ tab, position ->
            when(position){
                0 ->{
                    tab.text = "Airing Today"
                }
                1->{
                    tab.text = "Top Rated"
                }
                else ->{
                tab.text = "Popular"
            }

            }

        }.attach()

    }
}