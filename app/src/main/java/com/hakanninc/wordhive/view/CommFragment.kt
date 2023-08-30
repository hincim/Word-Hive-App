package com.hakanninc.wordhive.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.hakanninc.wordhive.R
import com.hakanninc.wordhive.databinding.FragmentCommBinding


class CommFragment : Fragment(R.layout.fragment_comm) {

    private var _fragmentBinding: FragmentCommBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCommBinding.bind(view)
        _fragmentBinding = binding

    }

    override fun onDestroy() {
        _fragmentBinding = null
        super.onDestroy()
    }

}