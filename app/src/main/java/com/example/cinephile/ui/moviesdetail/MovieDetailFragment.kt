package com.example.cinephile.ui.moviesdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.cinephile.databinding.MovieDetailFragmentBinding
import com.example.cinephile.ui.movies.MovieDetailViewModelFactory

class MovieDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {


        val application = requireNotNull(activity).application
        val binding = MovieDetailFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val ResultsItem = MovieDetailFragmentArgs.fromBundle(arguments!!).selectedProperty
        val viewModelFactory = MovieDetailViewModelFactory(ResultsItem,application)

        binding.movieDetailViewModel = ViewModelProviders.of(
            this,viewModelFactory).get(MovieDetailViewModel::class.java)

        return binding.root
    }

}
