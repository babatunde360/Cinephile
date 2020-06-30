package com.example.cinephile.ui.moviesdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinephile.databinding.MovieDetailFragmentBinding
import com.example.cinephile.ui.movies.MovieDetailViewModelFactory
import kotlinx.android.synthetic.main.movie_detail_fragment.*

class MovieDetailFragment : Fragment() {
    private lateinit var binding: MovieDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {


        val application = requireNotNull(activity).application
        binding = MovieDetailFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val resultsItem = MovieDetailFragmentArgs.fromBundle(requireArguments()).selectedProperty
        val viewModelFactory = MovieDetailViewModelFactory(resultsItem,application)


        binding.viewModel = ViewModelProvider(
            this,viewModelFactory).get(MovieDetailViewModel::class.java)

        binding.movieCast.movieCastRecyclerView.apply {
            adapter = CastAdapter()
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL,false)
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       NavigationUI.setupWithNavController(movie_detail_toolbar,findNavController())

    }
}