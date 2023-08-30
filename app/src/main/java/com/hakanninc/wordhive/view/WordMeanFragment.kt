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
import com.hakanninc.wordhive.adapter.MeanAdapter
import com.hakanninc.wordhive.data.remote.dto.tdk.TdkDto
import com.hakanninc.wordhive.databinding.FragmentWordMeanBinding
import com.hakanninc.wordhive.viewmodel.TdkViewModel


class WordMeanFragment : Fragment(R.layout.fragment_word_mean), SearchView.OnQueryTextListener {

    private lateinit var _fragmentBinding: FragmentWordMeanBinding
    private lateinit var viewModel : TdkViewModel
    private lateinit var meanAdapter: MeanAdapter
    private lateinit var list: ArrayList<TdkDto>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_word_mean,container,false)

        _fragmentBinding.toolbarMeans.title = "Türkçe Sözlük"
        (activity as AppCompatActivity).setSupportActionBar(_fragmentBinding.toolbarMeans)

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
        list = ArrayList()


        viewModel = ViewModelProvider(requireActivity())[TdkViewModel::class.java]
        meanAdapter = MeanAdapter(viewModel,list)
        _fragmentBinding.recyclerView.adapter = meanAdapter
        _fragmentBinding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        getData()

        _fragmentBinding.swipeRefreshLayout.setOnRefreshListener {
            _fragmentBinding.progressBarMean.visibility = View.VISIBLE
            _fragmentBinding.textViewNoData.visibility = View.GONE
            _fragmentBinding.recyclerView.visibility = View.GONE
            getData()
            _fragmentBinding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun getData(){
        viewModel.state.observe(viewLifecycleOwner, Observer {
            it?.let { data ->
                    if (data.isLoading){
                        _fragmentBinding.progressBarMean.visibility = View.VISIBLE
                        _fragmentBinding.textViewNoData.visibility = View.GONE
                    }else if (data.error == "Error"){
                        _fragmentBinding.textViewNoData.visibility = View.VISIBLE
                        _fragmentBinding.recyclerView.visibility = View.GONE
                        _fragmentBinding.progressBarMean.visibility = View.GONE
                    }
                    else{
                        list.clear()
                        _fragmentBinding.progressBarMean.visibility = View.GONE
                        _fragmentBinding.textViewNoData.visibility = View.GONE
                        _fragmentBinding.recyclerView.visibility = View.VISIBLE
                        list.add(it.mean!!)
                        meanAdapter.means = list
                        meanAdapter.notifyDataSetChanged()
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