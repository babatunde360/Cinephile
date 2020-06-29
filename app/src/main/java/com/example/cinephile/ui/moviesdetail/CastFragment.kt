package com.example.cinephile.ui.moviesdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinephile.databinding.FragmentMovieCastPagerBinding


class CastFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMovieCastPagerBinding.inflate(inflater)

        val viewModel: MovieDetailViewModel? =  parentFragment?.let {
            ViewModelProvider(it)
                .get(MovieDetailViewModel::class.java)
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.movieCastRecyclerView.apply {
            adapter = CastAdapter()
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL,false)
        }


        return binding.root
    }
}