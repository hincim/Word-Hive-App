package com.hakanninc.weatherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hakanninc.weatherapp.R
import com.hakanninc.weatherapp.databinding.FragmentHomeBinding
import com.hakanninc.weatherapp.viewmodel.WeatherViewModel


class HomeFragment : Fragment(R.layout.fragment_home) {

    private var fragmentBinding: FragmentHomeBinding? = null
    private lateinit var viewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[WeatherViewModel::class.java]

        val binding = FragmentHomeBinding.bind(view)
        fragmentBinding = binding

        viewModel.stateDetail.observe(viewLifecycleOwner, Observer {
5
            it?.let {
                if (it.isLoading){
                    binding.progressBar.visibility = View.VISIBLE
                }
                else{

                    binding.progressBar.visibility = View.GONE
                    binding.title.text = "Şehir: ${it.weather?.name}"
                    binding.degree.text = "Hava sıcaklığı: ${it.weather?.main?.temp}"
                    binding.description.text = "Açıklama: ${it.weather?.weather?.get(0)?.description}"
                }
            }
        })
    }

}