package com.example.cinephile.ui.series

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cinephile.databinding.FragmentTopRatedBinding

class TopRatedFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val binding = FragmentTopRatedBinding.inflate(inflater)

        val viewModel = ViewModelProvider(this).get(SeriesViewModel::class.java)
        binding.lifecycleOwner = this


        binding.topRatedRecyclerView.layoutManager = GridLayoutManager(activity,2)
        binding.topRatedRecyclerView.adapter = SeriesAdapter(SeriesAdapter.OnClickListener{
            viewModel.displayPropertyDetails(it)
        })
        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if(it != null){
                this.findNavController().navigate(SeriesFragmentDirections.actionNavSeriesToSeriesDetailFragment(it))
                viewModel.displayPropertyDetailsComplete()
            }
        })

        binding.viewModel = viewModel

        return binding.root
    }
}
