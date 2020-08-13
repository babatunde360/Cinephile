package com.example.cinephile.ui.series

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.cinephile.R
import com.example.cinephile.databinding.FragmentSeriesBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_series.*

class SeriesFragment : Fragment(){

    lateinit var binding: FragmentSeriesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = FragmentSeriesBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this

        val application = requireNotNull(activity).application
         val seriesViewModel =
            ViewModelProvider(this,SeriesViewModelFactory(application,context)).get(SeriesViewModel::class.java)


        binding.viewmodel = seriesViewModel

        binding.seriesViewPager.adapter = SeriesViewPagerAdapter(this)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val appBarConfiguration = AppBarConfiguration.Builder(R.id.nav_series).build()
        NavigationUI.setupWithNavController(series_toolbar,findNavController(),appBarConfiguration)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(series_toolbar)

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_settings ->{
                findNavController().navigate(SeriesFragmentDirections.actionOpenSettingsFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu,menu)
    }
}