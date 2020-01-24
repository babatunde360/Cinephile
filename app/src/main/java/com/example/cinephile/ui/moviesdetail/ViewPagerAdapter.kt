package com.example.cinephile.ui.moviesdetail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter (fragment: Fragment)
    : FragmentStateAdapter(fragment){


    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> {
                CastFragment()
            }
            1 -> {
                PagerTwo()
            }
            else -> {
                PagerThree()
            }
        }


    }

}
