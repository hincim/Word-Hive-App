package com.hakanninc.wordhive.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hakanninc.wordhive.R
import com.hakanninc.wordhive.adapter.MovieAdapter
import com.hakanninc.wordhive.event.MoviesEvent
import com.hakanninc.wordhive.state.MoviesState
import com.hakanninc.wordhive.viewmodel.MovieViewModel

class MovieFragment : Fragment(R.layout.fragment_movie), SearchView.OnQueryTextListener {
    private lateinit var _fragmentBinding: com.hakanninc.wordhive.databinding.FragmentMovieBinding
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var viewModel : MovieViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_movie,container,false)
        _fragmentBinding.toolbarMovie.title = "Film Ara"
        (activity as AppCompatActivity).setSupportActionBar(_fragmentBinding.toolbarMovie)

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {

                menuInflater.inflate(R.menu.menu_search,menu)
                val item = menu.findItem(R.id.action_search).actionView as SearchView
                item.setOnQueryTextListener(this@MovieFragment)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }

        },viewLifecycleOwner, Lifecycle.State.RESUMED)
        return _fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MovieViewModel::class.java]
        movieAdapter = MovieAdapter(MoviesState())
        _fragmentBinding.rvMovie.adapter = movieAdapter
        _fragmentBinding.rvMovie.layoutManager = LinearLayoutManager(view.context)

        getData()


    }


    private fun getData(){

        viewModel.state.observe(viewLifecycleOwner, Observer {
            it?.let { data ->
                when {
                    data.isLoading -> {
                        _fragmentBinding.progressBarMovie.visibility = View.VISIBLE
                        _fragmentBinding.textViewMovieNoData.visibility = View.GONE
                    }
                    data.error == "Error" -> {
                        _fragmentBinding.textViewMovieNoData.visibility = View.VISIBLE
                        _fragmentBinding.rvMovie.visibility = View.GONE
                        _fragmentBinding.progressBarMovie.visibility = View.GONE
                    }
                    else -> {
                        _fragmentBinding.progressBarMovie.visibility = View.GONE
                        _fragmentBinding.textViewMovieNoData.visibility = View.GONE
                        _fragmentBinding.rvMovie.visibility = View.VISIBLE
                        movieAdapter.movieList = it
                        movieAdapter.notifyDataSetChanged()
                    }
                }

            }
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            if (query.isEmpty())
                Log.e("submit", query.toString(), )
            viewModel.onEvent(MoviesEvent.Search(query))
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.e("change", newText.toString(), )
        return false
    }
}