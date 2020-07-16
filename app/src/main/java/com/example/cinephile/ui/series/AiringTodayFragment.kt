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
import com.example.cinephile.databinding.FragmentAiringTodayBinding
import com.example.cinephile.utils.MarginItemDecoration

class AiringTodayFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAiringTodayBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val application = requireNotNull(activity).application

        val seriesViewModel =
            ViewModelProvider(this,SeriesViewModelFactory(application,context))
            .get(SeriesViewModel::class.java)

        binding.seriesViewModel = seriesViewModel

        binding.airingTodayRecyclerView.apply {
            adapter = SeriesPagingAdapter(SeriesPagingAdapter.OnClickListener{
                seriesViewModel.displayPropertyDetails(it)
            })
            addItemDecoration(MarginItemDecoration(16))
            layoutManager = GridLayoutManager(activity,2)

        }
        seriesViewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if(it != null){
                this.findNavController().navigate(SeriesFragmentDirections.actionNavSeriesToSeriesDetailFragment(it))
                seriesViewModel.displayPropertyDetailsComplete()
            }
        })



        return binding.root
    }
}
