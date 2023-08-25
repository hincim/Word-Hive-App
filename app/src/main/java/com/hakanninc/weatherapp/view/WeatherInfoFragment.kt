package com.hakanninc.weatherapp.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.hakanninc.weatherapp.R
import com.hakanninc.weatherapp.databinding.FragmentWeatherInfoBinding
import com.hakanninc.weatherapp.viewmodel.WeatherViewModel
import java.util.*


class WeatherInfoFragment : Fragment(R.layout.fragment_weather_info), SearchView.OnQueryTextListener {

    private lateinit var _fragmentBinding: FragmentWeatherInfoBinding
    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _fragmentBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_weather_info,container,false)

        _fragmentBinding.toolbarTools.title = "Hava Durumu"
        (activity as AppCompatActivity).setSupportActionBar(_fragmentBinding.toolbarTools)

        requireActivity().addMenuProvider(object :MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {

                menuInflater.inflate(R.menu.menu_search,menu)
                val item = menu.findItem(R.id.action_search).actionView as SearchView
                item.setOnQueryTextListener(this@WeatherInfoFragment)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }

        },viewLifecycleOwner, Lifecycle.State.RESUMED)
        return _fragmentBinding.root


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[WeatherViewModel::class.java]

        getData()


        _fragmentBinding.swipeRefreshLayout.setOnRefreshListener {
            _fragmentBinding.progressBar.visibility = View.VISIBLE
            _fragmentBinding.textViewErr.visibility = View.GONE
            _fragmentBinding.textViewNoInternet.visibility = View.GONE
            _fragmentBinding.title.visibility = View.GONE
            _fragmentBinding.degree.visibility = View.GONE
            _fragmentBinding.description.visibility = View.GONE
            getData()
            _fragmentBinding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun getData(){
        viewModel.state.observe(viewLifecycleOwner, Observer {

            it?.let { data ->
                _fragmentBinding.let {
                    if (data.isLoading){
                        _fragmentBinding.progressBar.visibility = View.VISIBLE
                        _fragmentBinding.card.visibility = View.VISIBLE
                        _fragmentBinding.title.visibility = View.GONE
                        _fragmentBinding.degree.visibility = View.GONE
                        _fragmentBinding.description.visibility = View.GONE
                        _fragmentBinding.textViewErr.visibility = View.GONE
                        _fragmentBinding.textViewNoInternet.visibility = View.GONE
                    }else if (data.error == "No internet connection"){
                        _fragmentBinding.textViewErr.visibility = View.GONE
                        _fragmentBinding.textViewNoInternet.visibility = View.VISIBLE
                        _fragmentBinding.card.visibility = View.GONE
                        _fragmentBinding.progressBar.visibility = View.GONE
                    }else if (data.error == "Error"){
                        _fragmentBinding.textViewErr.visibility = View.VISIBLE
                        _fragmentBinding.textViewNoInternet.visibility = View.GONE
                        _fragmentBinding.card.visibility = View.GONE
                        _fragmentBinding.progressBar.visibility = View.GONE
                    }
                    else{
                        _fragmentBinding.progressBar.visibility = View.GONE
                        _fragmentBinding.textViewErr.visibility = View.GONE
                        _fragmentBinding.textViewNoInternet.visibility = View.GONE
                        _fragmentBinding.card.visibility = View.VISIBLE
                        _fragmentBinding.title.visibility = View.VISIBLE
                        _fragmentBinding.degree.visibility = View.VISIBLE
                        _fragmentBinding.description.visibility = View.VISIBLE
                        _fragmentBinding.title.text = "Şehir: ${data.weather?.name}"
                        _fragmentBinding.degree.text = "Hava sıcaklığı: ${data.weather?.main?.temp.toString().take(2)}°"
                        _fragmentBinding.description.text = "Hissedilen sıcaklık: ${data.weather?.main?.feels_like.toString().take(2)}°"
                    }
                }

            }
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            if (query.isEmpty())
                Log.e("submit", query.toString(), )
            viewModel.getWeatherInfo(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.e("change", newText.toString(), )
        return false
    }
}