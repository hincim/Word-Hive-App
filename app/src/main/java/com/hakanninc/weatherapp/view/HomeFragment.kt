package com.hakanninc.weatherapp.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.hakanninc.weatherapp.WordActivity
import com.hakanninc.weatherapp.R
import com.hakanninc.weatherapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _fragmentBinding: FragmentHomeBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        _fragmentBinding = binding


        binding.card.setOnClickListener {

            startActivity(Intent(it.context, WordActivity::class.java))
        }
    }


    override fun onDestroy() {
        _fragmentBinding = null
        super.onDestroy()
    }
}