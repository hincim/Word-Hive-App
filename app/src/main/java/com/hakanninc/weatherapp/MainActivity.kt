package com.hakanninc.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hakanninc.weatherapp.view.WeatherFragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor(
): AppCompatActivity() {

    @Inject
    lateinit var fragmentFactory: WeatherFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = fragmentFactory
        setContentView(R.layout.activity_main)

    }
}