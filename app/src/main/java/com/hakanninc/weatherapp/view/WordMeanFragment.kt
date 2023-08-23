package com.hakanninc.weatherapp.view

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
import com.hakanninc.weatherapp.R
import com.hakanninc.weatherapp.databinding.FragmentWordMeanBinding
import com.hakanninc.weatherapp.viewmodel.TdkViewModel
import com.hakanninc.weatherapp.viewmodel.WeatherViewModel


class WordMeanFragment : Fragment(R.layout.fragment_word_mean), SearchView.OnQueryTextListener {

    private lateinit var _fragmentBinding: FragmentWordMeanBinding
    private lateinit var viewModel : TdkViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_word_mean,container,false)

        _fragmentBinding.toolbarTools.title = "Türkçe Sözlük"
        (activity as AppCompatActivity).setSupportActionBar(_fragmentBinding.toolbarTools)

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {

                menuInflater.inflate(R.menu.menu_search,menu)
                val item = menu.findItem(R.id.action_search).actionView as SearchView
                item.setOnQueryTextListener(this@WordMeanFragment)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }

        },viewLifecycleOwner, Lifecycle.State.RESUMED)
        return _fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[TdkViewModel::class.java]
        getData()
        _fragmentBinding.swipeRefreshLayout.setOnRefreshListener {
            _fragmentBinding.progressBar.visibility = View.VISIBLE
            _fragmentBinding.textViewErr.visibility = View.GONE
            _fragmentBinding.textViewNoInternet.visibility = View.GONE
            _fragmentBinding.mean.visibility = View.GONE
            getData()
            _fragmentBinding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun getData(){
        viewModel.state.observe(viewLifecycleOwner, Observer {

            it?.let { data ->
                _fragmentBinding.let {
                    if (data.isLoading){
                        _fragmentBinding.progressBar.visibility = View.VISIBLE
                        _fragmentBinding.card.visibility = View.VISIBLE
                        _fragmentBinding.mean.visibility = View.GONE
                        _fragmentBinding.textViewErr.visibility = View.GONE
                        _fragmentBinding.textViewNoInternet.visibility = View.GONE
                    }else if (data.error == "No internet connection"){
                        _fragmentBinding.textViewErr.visibility = View.GONE
                        _fragmentBinding.textViewNoInternet.visibility = View.VISIBLE
                        _fragmentBinding.card.visibility = View.GONE
                        _fragmentBinding.progressBar.visibility = View.GONE
                    }else if (data.error == "Error"){
                        _fragmentBinding.textViewErr.visibility = View.VISIBLE
                        _fragmentBinding.textViewNoInternet.visibility = View.GONE
                        _fragmentBinding.card.visibility = View.GONE
                        _fragmentBinding.progressBar.visibility = View.GONE
                    }
                    else{
                        _fragmentBinding.progressBar.visibility = View.GONE
                        _fragmentBinding.textViewErr.visibility = View.GONE
                        _fragmentBinding.textViewNoInternet.visibility = View.GONE
                        _fragmentBinding.card.visibility = View.VISIBLE
                        _fragmentBinding.mean.visibility = View.VISIBLE
                        _fragmentBinding.mean.text = "${viewModel.searchName.value}: ${data.mean?.anlam}"
                    }
                }

            }
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            if (query.isEmpty())
                Log.e("submit", query.toString(), )
            viewModel.getWordMean(query.trim())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.e("change", newText.toString(), )
        return false
    }

}