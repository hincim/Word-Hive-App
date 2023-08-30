package com.hakanninc.wordhive.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.gms.ads.*

import com.hakanninc.wordhive.R
import com.hakanninc.wordhive.databinding.FragmentToolsBinding

class ToolsFragment : Fragment(R.layout.fragment_tools) {

    private var _fragmentBinding: FragmentToolsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentToolsBinding.bind(view)
        _fragmentBinding = binding

        binding.cardWeather.setOnClickListener {
            findNavController().navigate(ToolsFragmentDirections.actionToolsFragmentToWeatherInfoFragment())
        }
        binding.cardMean.setOnClickListener {
            findNavController().navigate(ToolsFragmentDirections.actionToolsFragmentToWordMeanFragment())
        }

        binding.cardMovie.setOnClickListener {
            findNavController().navigate(ToolsFragmentDirections.actionToolsFragmentToMovieFragment())
        }
        val adRequest = AdRequest.Builder().build()
        _fragmentBinding?.adView?.loadAd(adRequest)
    }
}