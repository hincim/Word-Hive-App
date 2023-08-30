package com.hakanninc.wordhive.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hakanninc.wordhive.R
import com.hakanninc.wordhive.databinding.ItemWordRowBinding
import com.hakanninc.wordhive.domain.model.Words

class WordsAdapter: RecyclerView.Adapter<WordsAdapter.WordsViewHolder>(){

    class WordsViewHolder(var binding: ItemWordRowBinding): RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object: DiffUtil.ItemCallback<Words>() {
        override fun areItemsTheSame(oldItem: Words, newItem: Words): Boolean {

            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Words, newItem: Words): Boolean {
            return oldItem == newItem
        }
    }
    private val recyclerListDiffer = AsyncListDiffer(this,diffUtil)
    var words: List<Words>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsViewHolder {
        val inflater = DataBindingUtil.inflate<ItemWordRowBinding>(LayoutInflater.from(parent.context),
            R.layout.item_word_row,parent,false)
        return WordsViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: WordsViewHolder, position: Int) {
       holder.binding.textViewEng.text = words[position].engWord
        holder.binding.textViewTr.text = words[position].trWord
    }

    override fun getItemCount(): Int {
        return words.size
    }
}