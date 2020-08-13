package com.example.cinephile.ui.movies

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.cinephile.R
import com.example.cinephile.databinding.FragmentMovieBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {

    lateinit var binding: FragmentMovieBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val application = requireNotNull(activity).application
         val viewModel: MovieViewModel by lazy {
            ViewModelProvider(this,MovieViewModelFactory(application,context)).get(MovieViewModel::class.java)
        }


        binding.viewmodel = viewModel
        binding.movieViewPager.adapter = MovieViewPagerAdapter(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        NavigationUI.setupWithNavController(movie_toolbar,findNavController())
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(movie_toolbar)

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_settings ->{
                findNavController().navigate(MovieFragmentDirections.actionOpenSettingsFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu,menu)
    }

}
