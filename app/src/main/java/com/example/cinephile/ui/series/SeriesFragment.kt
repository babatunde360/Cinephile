package com.example.cinephile.ui.series

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.cinephile.R

class SeriesFragment : Fragment(){
    private lateinit var seriesViewModel: SeriesViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        seriesViewModel = ViewModelProviders.of(this).get(SeriesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_series,container,false)

        return root
    }
}