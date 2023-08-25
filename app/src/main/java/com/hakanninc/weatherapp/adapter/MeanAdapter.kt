package com.hakanninc.weatherapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.hakanninc.weatherapp.R
import com.hakanninc.weatherapp.data.remote.dto.tdk.TdkDto
import com.hakanninc.weatherapp.databinding.ItemMeanRowBinding
import com.hakanninc.weatherapp.viewmodel.TdkViewModel

class MeanAdapter(private val viewModel: TdkViewModel, var means:ArrayList<TdkDto>): RecyclerView.Adapter<MeanAdapter.MeanViewHolder>() {

    class MeanViewHolder(val binding: ItemMeanRowBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeanViewHolder {
        val inflater = DataBindingUtil.inflate<ItemMeanRowBinding>(LayoutInflater.from(parent.context),
            R.layout.item_mean_row,parent,false)
        return MeanViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MeanViewHolder, @SuppressLint("RecyclerView") position: Int) {
        var mean = ""
        for ((index, value) in means[position].withIndex()){
            for ((i) in value.anlamlarListe){
                mean += "$i.\n\n"
                holder.binding.textViewMean.text = "${viewModel.searchName.value} \n$mean"
            }
        }
    }

    override fun getItemCount(): Int {
        return means.size
    }
}