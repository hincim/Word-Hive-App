package com.hakanninc.weatherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hakanninc.weatherapp.R
import com.hakanninc.weatherapp.databinding.FragmentHomeBinding
import com.hakanninc.weatherapp.databinding.FragmentToolsBinding
import com.hakanninc.weatherapp.viewmodel.TdkViewModel

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

    }
}