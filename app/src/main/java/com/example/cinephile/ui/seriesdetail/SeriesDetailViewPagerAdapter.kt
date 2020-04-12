package com.example.cinephile.ui.seriesdetail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SeriesDetailViewPagerAdapter (fragment: Fragment)
    :FragmentStateAdapter(fragment){
    override fun getItemCount(): Int {
        return 2

            }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0->{
                return SeriesCastFragment()
            }
            else->{
                return SeriesSeasonFragment()
            }
        }


    }

}