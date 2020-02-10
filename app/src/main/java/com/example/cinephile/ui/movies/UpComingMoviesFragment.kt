package com.example.cinephile.ui.movies


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cinephile.databinding.FragmentUpComingMoviesBinding

/**
 * A simple [Fragment] subclass.
 */
class UpComingMoviesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        val binding = FragmentUpComingMoviesBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = movieViewModel


        binding.movieRecyclerView.layoutManager = GridLayoutManager(activity, 2)
        binding.movieRecyclerView.adapter = MovieAdapter(MovieAdapter.OnClickListener {
            movieViewModel.displayPropertyDetails(it)
        })

        return binding.root
    }


}
