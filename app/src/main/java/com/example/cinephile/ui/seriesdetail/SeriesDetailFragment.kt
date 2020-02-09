package com.example.cinephile.ui.seriesdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.cinephile.databinding.SeriesDetailFragmentBinding

class SeriesDetailFragment : Fragment() {

    companion object {
        fun newInstance() = SeriesDetailFragment()
    }

    private lateinit var viewModel: SeriesDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = SeriesDetailFragmentBinding.inflate(inflater)


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SeriesDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
