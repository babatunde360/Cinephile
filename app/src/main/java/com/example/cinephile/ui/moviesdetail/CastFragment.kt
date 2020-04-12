package com.example.cinephile.ui.moviesdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
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

        viewModel?.movieCastList?.observe(viewLifecycleOwner, Observer {
        })

        val manager = GridLayoutManager(activity,2)
        binding.castRecyclerView.layoutManager = manager
        binding.castRecyclerView.adapter = CastFragmentAdapter()

        return binding.root
    }
}