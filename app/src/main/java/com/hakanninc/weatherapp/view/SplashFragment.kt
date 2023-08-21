package com.hakanninc.weatherapp.view

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.hakanninc.weatherapp.R
import com.hakanninc.weatherapp.databinding.FragmentSplashBinding

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private var _fragmentBinding: FragmentSplashBinding ?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSplashBinding.bind(view)
        _fragmentBinding = binding

        ObjectAnimator.ofFloat(binding.imageView5,"rotationY",0.0f,-360.0f).apply {
            duration = 500

        }.start()

        object : CountDownTimer(2000,1000){
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                findNavController()
                    .navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            }

        }.start()
    }


    override fun onDestroy() {
        super.onDestroy()
        _fragmentBinding = null
    }
}