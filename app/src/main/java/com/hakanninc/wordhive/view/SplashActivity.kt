package com.hakanninc.wordhive.view

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.hakanninc.wordhive.MainActivity
import com.hakanninc.wordhive.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MobileAds.initialize(this) {}
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