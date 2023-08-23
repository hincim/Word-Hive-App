package com.hakanninc.weatherapp.view

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.hakanninc.weatherapp.R
import com.hakanninc.weatherapp.databinding.FragmentWordAddBinding
import com.hakanninc.weatherapp.domain.model.Words
import com.hakanninc.weatherapp.viewmodel.WordsViewModel

class WordAddFragment : Fragment(R.layout.fragment_word_add) {

    private var _fragmentBinding: FragmentWordAddBinding? = null
    private lateinit var viewModel: WordsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentWordAddBinding.bind(view)
        _fragmentBinding = binding


        ObjectAnimator.ofFloat(binding.welcomeBack,"alpha",0.0f,1.0f).apply {
            duration = 600

        }.start()

        viewModel = ViewModelProvider(requireActivity())[WordsViewModel::class.java]

        binding.fabBack.setOnClickListener {
            findNavController().popBackStack()
        }

       binding.buttonSave.setOnClickListener {
           val eng = binding.editTextEng.text.toString()
           val tr = binding.editTextTurkish.text.toString()
           if (eng.isEmpty() || tr.isEmpty()){
               Toast.makeText(context,"Boş değer girilmemeli",Toast.LENGTH_SHORT).show()
           }else{
               val listWords = arrayListOf<Words>()
               val words = Words(eng,tr)
               listWords.add(words)
               viewModel.saveInSQLite(listWords)
               Navigation.findNavController(it).popBackStack()
           }
       }

    }
    override fun onDestroy() {
        _fragmentBinding = null
        super.onDestroy()
    }
}