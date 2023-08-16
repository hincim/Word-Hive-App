package com.hakanninc.weatherapp.view

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.hakanninc.weatherapp.R
import com.hakanninc.weatherapp.adapter.WordsAdapter
import com.hakanninc.weatherapp.databinding.FragmentHomeBinding
import com.hakanninc.weatherapp.databinding.FragmentListAddBinding
import com.hakanninc.weatherapp.domain.model.Words
import com.hakanninc.weatherapp.viewmodel.WordsAddViewModel


class ListAddFragment : Fragment(R.layout.fragment_list_add) {

    private var _fragmentBinding: FragmentListAddBinding? = null
    private val wordsAdapter: WordsAdapter = WordsAdapter(arrayListOf())
    private lateinit var viewModel: WordsAddViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentListAddBinding.bind(view)
        _fragmentBinding = binding
        viewModel = ViewModelProvider(requireActivity())[WordsAddViewModel::class.java]
        binding.toolbar.title = "Kelimeler"

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        binding.rv.layoutManager = LinearLayoutManager(context)
        binding.rv.adapter = wordsAdapter

        viewModel.getDataFromSQLite()

        viewModel.words.observe(viewLifecycleOwner, Observer {
            it?.let {
                wordsAdapter.wordsList = it
                wordsAdapter.notifyDataSetChanged()
            }
        })

        binding.fab.setOnClickListener {
            Navigation.findNavController(it).navigate(ListAddFragmentDirections.actionListAddFragmentToWordAddFragment())
        }
    }
}