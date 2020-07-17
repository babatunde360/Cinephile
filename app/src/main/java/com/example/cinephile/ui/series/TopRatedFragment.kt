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
import com.example.cinephile.utils.MarginItemDecoration

class TopRatedFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val binding =
           FragmentTopRatedBinding.inflate(inflater,container,false)

        val application = requireNotNull(activity).application
        val seriesViewModel =
            ViewModelProvider(this,SeriesViewModelFactory(application,context))
                .get(SeriesViewModel::class.java)
        binding.lifecycleOwner = this

        binding.topRatedRecyclerView.apply {
            layoutManager = GridLayoutManager(activity,2)
            addItemDecoration(MarginItemDecoration(16))
            adapter = SeriesPagingAdapter(SeriesPagingAdapter.OnClickListener{
                seriesViewModel.displayPropertyDetails(it)
            })
        }



        seriesViewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if(it != null){
                this.findNavController().navigate(SeriesFragmentDirections.actionNavSeriesToSeriesDetailFragment(it))
                seriesViewModel.displayPropertyDetailsComplete()
            }
        })

        binding.viewModel = seriesViewModel

        return binding.root
    }
}
