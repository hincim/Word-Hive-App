package com.hakanninc.wordhive.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hakanninc.wordhive.R
import com.hakanninc.wordhive.databinding.FragmentMovieDetailBinding
import com.hakanninc.wordhive.viewmodel.MovieDetailViewModel


class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {
    private var _fragmentBinding: FragmentMovieDetailBinding? = null
    private lateinit var viewModel : MovieDetailViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MovieDetailViewModel::class.java]

        val binding = FragmentMovieDetailBinding.bind(view)
        _fragmentBinding = binding

        arguments?.let {

            viewModel.getMovieDetail(MovieDetailFragmentArgs.fromBundle(it).movieId)
        }

        viewModel.state.observe(viewLifecycleOwner, Observer {
            it?.let { data ->
                when {
                    data.isLoading -> {
                        binding.progressBarMovieDetail.visibility = View.VISIBLE
                        binding.textViewErrorMovieDetail.visibility = View.GONE
                        binding.movieImdb.visibility = View.GONE
                        binding.movieImdbTitle.visibility = View.GONE
                        binding.movieName.visibility = View.GONE
                        binding.movieNameTitle.visibility = View.GONE
                        binding.movieYear.visibility = View.GONE
                        binding.movieYearTitle.visibility = View.GONE
                        binding.moviePlot.visibility = View.GONE
                        binding.moviePlotTitle.visibility = View.GONE
                    }
                    data.error == "Error" -> {
                        binding.progressBarMovieDetail.visibility = View.GONE
                        binding.textViewErrorMovieDetail.visibility = View.VISIBLE
                        binding.movieImdb.visibility = View.GONE
                        binding.movieImdbTitle.visibility = View.GONE
                        binding.movieName.visibility = View.GONE
                        binding.movieNameTitle.visibility = View.GONE
                        binding.movieYear.visibility = View.GONE
                        binding.movieYearTitle.visibility = View.GONE
                        binding.moviePlot.visibility = View.GONE
                        binding.moviePlotTitle.visibility = View.GONE
                    }
                    else -> {
                        binding.progressBarMovieDetail.visibility = View.GONE
                        binding.textViewErrorMovieDetail.visibility = View.GONE
                        binding.movieImdb.visibility = View.VISIBLE
                        binding.movieImdbTitle.visibility = View.VISIBLE
                        binding.movieName.visibility = View.VISIBLE
                        binding.movieNameTitle.visibility = View.VISIBLE
                        binding.movieYear.visibility = View.VISIBLE
                        binding.movieYearTitle.visibility = View.VISIBLE
                        binding.moviePlot.visibility = View.VISIBLE
                        binding.moviePlotTitle.visibility = View.VISIBLE
                        binding.selectedMovie = it.movies
                    }
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentBinding = null
    }
}