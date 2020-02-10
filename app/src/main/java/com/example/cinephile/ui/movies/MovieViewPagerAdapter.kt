package com.example.cinephile.ui.movies

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


class MovieViewPagerAdapter(fragment: Fragment)
    :FragmentStateAdapter(fragment){
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
        0->{
            PopularMovieFragment()
        }else->{
                UpComingMoviesFragment()
            }
        }

    }

}