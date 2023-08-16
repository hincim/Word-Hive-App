package com.hakanninc.weatherapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hakanninc.weatherapp.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}