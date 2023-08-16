package com.hakanninc.weatherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.hakanninc.weatherapp.R
import com.hakanninc.weatherapp.databinding.FragmentHomeBinding
import com.hakanninc.weatherapp.databinding.FragmentListAddBinding


class ListAddFragment : Fragment(R.layout.fragment_list_add) {

    private var _fragmentBinding: FragmentListAddBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentListAddBinding.bind(view)
        _fragmentBinding = binding

        binding.fab.setOnClickListener {
            Navigation.findNavController(it).navigate(ListAddFragmentDirections.actionListAddFragmentToWordAddFragment())
        }
    }
}