package com.hakanninc.weatherapp.view

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.hakanninc.weatherapp.R
import com.hakanninc.weatherapp.databinding.FragmentToolsBinding
import com.hakanninc.weatherapp.state.WeatherDetailState
import com.hakanninc.weatherapp.viewmodel.WeatherViewModel
import java.util.*
import kotlin.math.log


class ToolsFragment : Fragment(R.layout.fragment_tools), SearchView.OnQueryTextListener {

    private lateinit var fragmentBinding: FragmentToolsBinding
    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        fragmentBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_tools,container,false)

        fragmentBinding.toolbarTools.title = "Araçlar"
        (activity as AppCompatActivity).setSupportActionBar(fragmentBinding.toolbarTools)

        requireActivity().addMenuProvider(object :MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {

                menuInflater.inflate(R.menu.menu_search,menu)
                val item = menu.findItem(R.id.action_search).actionView as SearchView
                item.setOnQueryTextListener(this@ToolsFragment)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }

        },viewLifecycleOwner, Lifecycle.State.RESUMED)
        return fragmentBinding.root


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[WeatherViewModel::class.java]

            getData()

        fragmentBinding.swipeRefreshLayout.setOnRefreshListener {
            fragmentBinding.progressBar.visibility = View.VISIBLE
            fragmentBinding.title.visibility = View.GONE
            fragmentBinding.degree.visibility = View.GONE
            fragmentBinding.description.visibility = View.GONE
            getData()
            fragmentBinding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun getData(){
        viewModel.stateDetail.observe(viewLifecycleOwner, Observer {

            it?.let { data ->
                fragmentBinding.let {
                    if (data.isLoading){
                        fragmentBinding.progressBar.visibility = View.VISIBLE
                        fragmentBinding.card.visibility = View.VISIBLE
                        fragmentBinding.title.visibility = View.GONE
                        fragmentBinding.degree.visibility = View.GONE
                        fragmentBinding.description.visibility = View.GONE
                        fragmentBinding.textViewErr.visibility = View.GONE
                    }else if (data.error.isNotEmpty()){
                        fragmentBinding.textViewErr.visibility = View.VISIBLE
                        fragmentBinding.card.visibility = View.GONE
                        fragmentBinding.progressBar.visibility = View.GONE
                    }
                    else{
                        fragmentBinding.progressBar.visibility = View.GONE
                        fragmentBinding.textViewErr.visibility = View.GONE
                        fragmentBinding.card.visibility = View.VISIBLE
                        fragmentBinding.title.visibility = View.VISIBLE
                        fragmentBinding.degree.visibility = View.VISIBLE
                        fragmentBinding.description.visibility = View.VISIBLE
                        fragmentBinding.title.text = "Şehir: ${data.weather?.name}"
                        fragmentBinding.degree.text = "Hava sıcaklığı: ${data.weather?.main?.temp.toString().take(2)}°"
                        fragmentBinding.description.text = "Hissedilen sıcaklık: ${data.weather?.main?.feels_like.toString().take(2)}°"
                        if (data.weather?.weather?.get(0)?.id!! < 600 && Calendar.HOUR_OF_DAY>= 19 || Calendar.HOUR_OF_DAY <=5){
                            fragmentBinding.image.setImageResource(R.drawable.night)
                        }else if (data.weather.weather[0].id< 600 ){
                            fragmentBinding.image.setImageResource(R.drawable.cloudy)
                        }else if (Calendar.HOUR_OF_DAY>= 19 || Calendar.HOUR_OF_DAY <=5){
                            fragmentBinding.image.setImageResource(R.drawable.night)
                        }
                        else{
                            fragmentBinding.image.setImageResource(R.drawable.sunny)
                        }
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