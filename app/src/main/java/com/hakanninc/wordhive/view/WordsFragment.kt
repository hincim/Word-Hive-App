package com.hakanninc.wordhive.view

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hakanninc.wordhive.R
import com.hakanninc.wordhive.adapter.WordsAdapter
import com.hakanninc.wordhive.databinding.FragmentWordsBinding
import com.hakanninc.wordhive.domain.model.Words
import com.hakanninc.wordhive.viewmodel.WordsViewModel


class WordsFragment : Fragment(R.layout.fragment_words), SearchView.OnQueryTextListener {

    private var _fragmentBinding: FragmentWordsBinding? = null
    private val wordsAdapter: WordsAdapter = WordsAdapter()
    private lateinit var viewModel: WordsViewModel

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

            showCustomDialogBox(selectedWords)

        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[WordsViewModel::class.java]

        val binding = FragmentWordsBinding.bind(view)
        _fragmentBinding = binding

        subscribeToObserves()

        binding.rv.adapter = wordsAdapter
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        ItemTouchHelper(swipeCallBack).attachToRecyclerView(binding.rv)

        binding.fab.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(WordsFragmentDirections.actionListAddFragmentToWordAddFragment())
        }

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        requireActivity().addMenuProvider(object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_search,menu)
                val item = menu.findItem(R.id.action_search).actionView as SearchView
                item.setOnQueryTextListener(this@WordsFragment)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }

        },viewLifecycleOwner,Lifecycle.State.RESUMED)

    }

    private fun subscribeToObserves() {
        viewModel.getDataFromSQLite()

        viewModel.words.observe(viewLifecycleOwner, Observer {
            it?.let {
                wordsAdapter.words = it
            }
        })
    }

    override fun onDestroy() {
        _fragmentBinding = null
        super.onDestroy()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            if (query.isEmpty())
            viewModel.getWordBySearch(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            viewModel.getWordBySearch(newText)
        }
        return true
    }

    private fun showCustomDialogBox(selectedWords: Words){
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_custom_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val message: TextView = dialog.findViewById(R.id.message)
        val btnYes: Button = dialog.findViewById(R.id.btnYes)
        val btnCancel: Button = dialog.findViewById(R.id.btnCancel)

        message.text = "${selectedWords.engWord} silinsin mi?"

        btnYes.setOnClickListener {
            viewModel.deleteWords(selectedWords)
            subscribeToObserves()
            wordsAdapter.notifyDataSetChanged()
            dialog.dismiss()
        }

        btnCancel.setOnClickListener {
            wordsAdapter.notifyDataSetChanged()
            dialog.dismiss()
        }
        dialog.show()
    }
}







