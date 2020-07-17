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
import com.example.cinephile.databinding.FragmentPopularSeriesBinding
import com.example.cinephile.utils.MarginItemDecoration

/**
 * A simple [Fragment] subclass.
 */
class PopularSeriesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            FragmentPopularSeriesBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this


        val application = requireNotNull(activity).application

        val viewModel =
            ViewModelProvider(this,SeriesViewModelFactory(application,context)).
            get(SeriesViewModel::class.java)

        binding.viewModel = viewModel
        binding.popularSeriesRecyclerView.apply{
            adapter = SeriesPagingAdapter(SeriesPagingAdapter.OnClickListener{
                viewModel.displayPropertyDetails(it)
            })
            addItemDecoration(MarginItemDecoration(16))
            layoutManager = GridLayoutManager(activity,2)

        }


        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if (it!=null){
                this.findNavController().navigate(SeriesFragmentDirections.actionNavSeriesToSeriesDetailFragment(it))
                viewModel.displayPropertyDetailsComplete()
            }
        })

        return binding.root
    }


}
