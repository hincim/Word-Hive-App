package com.hakanninc.weatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.hakanninc.weatherapp.R
import com.hakanninc.weatherapp.data.remote.dto.movie.MoviesDto
import com.hakanninc.weatherapp.data.remote.dto.movie.Search
import com.hakanninc.weatherapp.databinding.ItemMovieRowBinding
import com.hakanninc.weatherapp.state.MoviesState
import com.hakanninc.weatherapp.view.MovieFragmentDirections

class MovieAdapter(var movieList: MoviesState): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    class MovieViewHolder(val binding: ItemMovieRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = DataBindingUtil.inflate<ItemMovieRowBinding>(LayoutInflater.from(parent.context),
            R.layout.item_movie_row,parent,false)
        return MovieViewHolder(view)
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binding.movie = movieList.movies[position]
        holder.binding.cardViewMovie.setOnClickListener {
            Navigation.findNavController(it).navigate(MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(movieList.movies[position].imdbID))
        }
    }

    override fun getItemCount(): Int {
        return movieList.movies.size
    }

}