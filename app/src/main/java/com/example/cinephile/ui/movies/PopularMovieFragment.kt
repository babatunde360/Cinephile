package com.example.cinephile.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cinephile.databinding.FragmentPopularMovieBinding


class PopularMovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application

        val movieViewModel =
            ViewModelProvider(this,MovieViewModelFactory(application,context))
            .get(MovieViewModel::class.java)

        val binding = FragmentPopularMovieBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = movieViewModel

        binding.movieRecyclerView.layoutManager = GridLayoutManager(activity,2)
        binding.movieRecyclerView.adapter = MovieAdapter(MovieAdapter.OnClickListener {
            movieViewModel.displayPropertyDetails(it)
        })
        movieViewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if(it != null){
                this.findNavController().navigate(MovieFragmentDirections.actionShowDetail(it))
                movieViewModel.displayPropertyDetailsComplete()
            }
        })


        return binding.root
    }
}