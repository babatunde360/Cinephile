package com.example.cinephile.ui.series

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SeriesViewPagerAdapter (fragment: Fragment)
    :FragmentStateAdapter(fragment){
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 ->{
                AiringTodayFragment()
            }
            else -> {
                TopRatedFragment()
            }
        }
    }

}
