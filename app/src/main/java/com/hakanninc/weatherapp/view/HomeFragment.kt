package com.hakanninc.weatherapp.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.hakanninc.weatherapp.R
import com.hakanninc.weatherapp.databinding.FragmentHomeBinding
import com.hakanninc.weatherapp.viewmodel.WeatherViewModel
import kotlinx.coroutines.launch
import java.util.*


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

            getData()

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.progressBar.visibility = View.VISIBLE
            fragmentBinding!!.title.visibility = View.GONE
            fragmentBinding!!.degree.visibility = View.GONE
            fragmentBinding!!.description.visibility = View.GONE
            getData()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun getData(){
        viewModel.stateDetail.observe(viewLifecycleOwner, Observer {

            it?.let { data ->
                fragmentBinding?.let {
                    if (data.isLoading){
                        fragmentBinding!!.progressBar.visibility = View.VISIBLE
                        fragmentBinding!!.title.visibility = View.GONE
                        fragmentBinding!!.degree.visibility = View.GONE
                        fragmentBinding!!.description.visibility = View.GONE
                    }
                    else{
                        fragmentBinding!!.progressBar.visibility = View.GONE
                        fragmentBinding!!.title.visibility = View.VISIBLE
                        fragmentBinding!!.degree.visibility = View.VISIBLE
                        fragmentBinding!!.description.visibility = View.VISIBLE
                        fragmentBinding!!.title.text = "Şehir: ${data.weather?.name}"
                        fragmentBinding!!.degree.text = "Hava sıcaklığı: ${data.weather?.main?.temp.toString().take(2)}°"
                        fragmentBinding!!.description.text = "Hissedilen sıcaklık: ${data.weather?.main?.feels_like.toString().take(2)}°"
                        if (data.weather?.weather?.get(0)?.id!! < 600 && Calendar.HOUR_OF_DAY>= 19 || Calendar.HOUR_OF_DAY <=5){
                            fragmentBinding!!.image.setImageResource(R.drawable.night)
                        }else if (data.weather.weather[0].id< 600 ){
                            fragmentBinding!!.image.setImageResource(R.drawable.cloudy)
                        }else if (Calendar.HOUR_OF_DAY>= 19 || Calendar.HOUR_OF_DAY <=5){
                            fragmentBinding!!.image.setImageResource(R.drawable.night)
                        }
                        else{
                            fragmentBinding!!.image.setImageResource(R.drawable.sunny)
                        }
                    }
                }

            }
        })
    }

}