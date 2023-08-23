package com.hakanninc.weatherapp.view

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.hakanninc.weatherapp.MainActivity
import com.hakanninc.weatherapp.R
import com.hakanninc.weatherapp.WordActivity
import com.hakanninc.weatherapp.databinding.ActivitySplashBinding
import com.hakanninc.weatherapp.databinding.ActivityWordBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ObjectAnimator.ofFloat(binding.imageView,"rotationY",0.0f,-360.0f).apply {
            duration = 500

        }.start()

        object : CountDownTimer(2000,1000){
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }

        }.start()
    }
}