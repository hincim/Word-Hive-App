package com.hakanninc.weatherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hakanninc.weatherapp.R


class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            println(MovieDetailFragmentArgs.fromBundle(it).movieId)
        }

    }
}