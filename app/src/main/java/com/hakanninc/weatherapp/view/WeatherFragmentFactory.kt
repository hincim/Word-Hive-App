package com.hakanninc.weatherapp.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject

class WeatherFragmentFactory @Inject constructor(

): FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            HomeFragment::class.java.name -> HomeFragment()
            else -> super.instantiate(classLoader, className)
        }
    }
}