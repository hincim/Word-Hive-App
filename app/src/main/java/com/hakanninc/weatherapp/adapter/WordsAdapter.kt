package com.hakanninc.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hakanninc.weatherapp.R
import com.hakanninc.weatherapp.databinding.ItemWordRowBinding
import com.hakanninc.weatherapp.domain.model.Words

class WordsAdapter(var wordsList: List<Words>): RecyclerView.Adapter<WordsAdapter.WordsViewHolder>(){

    class WordsViewHolder(var binding: ItemWordRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsViewHolder {
        val inflater = DataBindingUtil.inflate<ItemWordRowBinding>(LayoutInflater.from(parent.context),
            R.layout.item_word_row,parent,false)
        return WordsViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: WordsViewHolder, position: Int) {
       holder.binding.textViewEng.text = wordsList[position].engWord
        holder.binding.textViewTr.text = wordsList[position].trWord
    }

    override fun getItemCount(): Int {
        return wordsList.size
    }
}