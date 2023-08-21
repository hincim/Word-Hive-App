package com.hakanninc.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hakanninc.weatherapp.databinding.ActivityWordBinding

class WordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}