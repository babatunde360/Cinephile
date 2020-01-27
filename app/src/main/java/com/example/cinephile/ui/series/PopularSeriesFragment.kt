package com.example.cinephile.ui.series


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cinephile.databinding.FragmentPopularSeriesBinding

/**
 * A simple [Fragment] subclass.
 */
class PopularSeriesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPopularSeriesBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val viewModel = ViewModelProviders.of(this).get(SeriesViewModel::class.java)
        binding.viewModel = viewModel

        binding.popularSeriesRecyclerView.adapter = SeriesAdapter()
        binding.popularSeriesRecyclerView.layoutManager = GridLayoutManager(activity,2)

        return binding.root
    }


}
