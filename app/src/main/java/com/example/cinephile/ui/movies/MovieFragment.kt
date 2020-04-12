package com.example.cinephile.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cinephile.databinding.FragmentMovieBinding
import com.google.android.material.tabs.TabLayoutMediator

class MovieFragment : Fragment() {

    lateinit var binding: FragmentMovieBinding

    private val viewModel: MovieViewModel by lazy {
        ViewModelProvider(this).get(MovieViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewmodel = viewModel
        binding.movieViewPager.adapter = MovieViewPagerAdapter(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val movieTab = binding.movieTab
        val movieViewPager = binding.movieViewPager

        TabLayoutMediator(movieTab,movieViewPager){tab, position ->
            when(position){
                0 ->{
                    tab.text = "Popular"
                }else->{
                tab.text = "Upcoming"
            }
            }

        }.attach()
    }
}
