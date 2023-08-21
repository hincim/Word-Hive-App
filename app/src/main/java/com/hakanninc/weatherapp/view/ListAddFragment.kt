package com.hakanninc.weatherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hakanninc.weatherapp.R
import com.hakanninc.weatherapp.adapter.WordsAdapter
import com.hakanninc.weatherapp.databinding.FragmentListAddBinding
import com.hakanninc.weatherapp.viewmodel.WordsAddViewModel


class ListAddFragment : Fragment(R.layout.fragment_list_add) {

    private var _fragmentBinding: FragmentListAddBinding? = null
    private val wordsAdapter: WordsAdapter = WordsAdapter()
    private lateinit var viewModel: WordsAddViewModel

    private val swipeCallBack = object : ItemTouchHelper.SimpleCallback(
        0,
        ItemTouchHelper.LEFT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutPosition = viewHolder.layoutPosition
            val selectedWords = wordsAdapter.words[layoutPosition]
            viewModel.deleteWords(selectedWords)
            subscribeToObserves()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[WordsAddViewModel::class.java]

        val binding = FragmentListAddBinding.bind(view)
        _fragmentBinding = binding
        binding.toolbar.title = "Kelimeler"

        subscribeToObserves()

        binding.rv.adapter = wordsAdapter
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        ItemTouchHelper(swipeCallBack).attachToRecyclerView(binding.rv)



        binding.fab.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(ListAddFragmentDirections.actionListAddFragmentToWordAddFragment())
        }
    }

    private fun subscribeToObserves() {
        viewModel.getDataFromSQLite()

        viewModel.words.observe(viewLifecycleOwner, Observer {
            it?.let {
                wordsAdapter.words = it
            }
            wordsAdapter.notifyDataSetChanged()
        })
    }

    override fun onDestroy() {
        _fragmentBinding = null
        super.onDestroy()
    }
}