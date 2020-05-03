package com.example.cinephile.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cinephile.databinding.FragmentMovieBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

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

        viewModel.connected.observe(viewLifecycleOwner, Observer {
            if (it==true){
                networkStatus.visibility = View.GONE
            }else{
                networkStatus.visibility == View.VISIBLE
            }
        })

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
