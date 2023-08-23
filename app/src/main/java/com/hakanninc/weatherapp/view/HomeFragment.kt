package com.hakanninc.weatherapp.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hakanninc.weatherapp.WordActivity
import com.hakanninc.weatherapp.R
import com.hakanninc.weatherapp.databinding.FragmentHomeBinding
import com.hakanninc.weatherapp.viewmodel.TdkViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _fragmentBinding: FragmentHomeBinding? = null

    lateinit var viewModel: TdkViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[TdkViewModel::class.java]

        val binding = FragmentHomeBinding.bind(view)
        _fragmentBinding = binding

        viewModel.state.observe(viewLifecycleOwner, Observer {
            it?.let { data ->
                print(data.mean?.anlam)
            }
        })


        binding.card.setOnClickListener {

            startActivity(Intent(it.context, WordActivity::class.java))
        }
    }


    override fun onDestroy() {
        _fragmentBinding = null
        super.onDestroy()
    }
}