package com.hakanninc.weatherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.hakanninc.weatherapp.R
import com.hakanninc.weatherapp.databinding.FragmentHomeBinding
import com.hakanninc.weatherapp.databinding.FragmentWordAddBinding
import com.hakanninc.weatherapp.domain.model.Words
import com.hakanninc.weatherapp.viewmodel.WordsAddViewModel

class WordAddFragment : Fragment(R.layout.fragment_word_add) {

    private var _fragmentBinding: FragmentWordAddBinding? = null
    private lateinit var viewModel: WordsAddViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentWordAddBinding.bind(view)
        _fragmentBinding = binding

        viewModel = ViewModelProvider(requireActivity())[WordsAddViewModel::class.java]

       binding.buttonSave.setOnClickListener {
           val eng = binding.editTextEng.text.toString()
           println(eng)
           val tr = binding.editTextTurkish.text.toString()
           val listWords = arrayListOf<Words>()
           val words = Words(eng,tr)
           listWords.add(words)
           viewModel.saveInSQLite(listWords)
           Navigation.findNavController(it).popBackStack()
       }

    }
}