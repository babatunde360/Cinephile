package com.example.cinephile.ui.moviesdetail

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinephile.databinding.MovieDetailFragmentBinding
import com.example.cinephile.ui.movies.MovieDetailViewModelFactory
import com.example.cinephile.utils.CastMarginItemDecoration
import kotlinx.android.synthetic.main.movie_detail_fragment.*
import timber.log.Timber


class MovieDetailFragment : Fragment() {
    private lateinit var binding: MovieDetailFragmentBinding
    private lateinit var viewModel:MovieDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {


        val application = requireNotNull(activity).application
        binding = MovieDetailFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val resultsItem = MovieDetailFragmentArgs.fromBundle(requireArguments()).selectedProperty
        val viewModelFactory = MovieDetailViewModelFactory(resultsItem,application)


         viewModel = ViewModelProvider(
            this,viewModelFactory).get(MovieDetailViewModel::class.java)

        binding.viewModel = viewModel

        binding.movieCast.movieCastRecyclerView.apply {
            adapter = CastAdapter()
            addItemDecoration(CastMarginItemDecoration(16))
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL,false)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       NavigationUI.setupWithNavController(movie_detail_toolbar,findNavController())
        viewModel.selectedProperty.observe(viewLifecycleOwner, Observer {
            if(it!=null) {
                if(it.video != null)
                if (it.video) {
                    binding.movieCast.trailerFAB.visibility = View.GONE
                } else {
                    binding.movieCast.trailerFAB.visibility = View.VISIBLE
                }
            }
        })
        viewModel.movieVideo.observe(viewLifecycleOwner, Observer {
         val videoResult =  it.find { result->
                result.site == "YouTube"
            }
            binding.movieCast.trailerFAB.setOnClickListener {
                val youtubeIntent = Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/watch?v=${videoResult?.key}"))
                try {
                    context?.startActivity(youtubeIntent)
                } catch (ex: ActivityNotFoundException) {
                       Timber.d(ex)
                }
            }

        })


    }
}