package com.example.cinephile.ui.moviesdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.cinephile.databinding.MovieDetailFragmentBinding
import com.example.cinephile.ui.movies.MovieDetailViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator

class MovieDetailFragment : Fragment() {
    private lateinit var binding: MovieDetailFragmentBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {


        val application = requireNotNull(activity).application
        binding = MovieDetailFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        viewPagerAdapter = ViewPagerAdapter(this)

        binding.viewPager.adapter = viewPagerAdapter


        val resultsItem = MovieDetailFragmentArgs.fromBundle(arguments!!).selectedProperty
        val viewModelFactory = MovieDetailViewModelFactory(resultsItem,application)

        resultsItem.id


        binding.movieDetailViewModel = ViewModelProviders.of(
            this,viewModelFactory).get(MovieDetailViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tabLayout = binding.tabs
        TabLayoutMediator(tabLayout, binding.viewPager){tab, position ->
            when(position){
                0 ->{
                    tab.text = "Cast"
                }
                1 ->{
                    tab.text = "Screening"
                }
                2 ->{
                    tab.text = "episodes"
                }
            }
        }.attach()

    }
}