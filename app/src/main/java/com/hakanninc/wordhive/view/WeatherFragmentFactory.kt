package com.hakanninc.wordhive.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject

class WeatherFragmentFactory @Inject constructor(

): FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            WeatherInfoFragment::class.java.name -> WeatherInfoFragment()
            else -> super.instantiate(classLoader, className)
        }
    }
}