package com.hakanninc.weatherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.hakanninc.weatherapp.R
import com.hakanninc.weatherapp.databinding.FragmentCommBinding


class CommFragment : Fragment(R.layout.fragment_comm) {

    private var fragmentBinding: FragmentCommBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCommBinding.bind(view)
        fragmentBinding = binding

    }

}