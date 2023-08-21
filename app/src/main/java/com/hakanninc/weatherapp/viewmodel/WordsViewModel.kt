package com.hakanninc.weatherapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.hakanninc.weatherapp.data.room.WordsDatabase
import com.hakanninc.weatherapp.domain.model.Words
import kotlinx.coroutines.launch

class WordsViewModel(application: Application): BaseViewModel(application) {

    val words = MutableLiveData<List<Words>>()

    private fun showWords(wordsList: List<Words>){
        words.value = wordsList
    }

    fun getDataFromSQLite(){

        launch {
            val words = WordsDatabase(getApplication()).wordsDao().getAllWords()
            showWords(words)
        }
    }

    fun saveInSQLite(words: List<Words>){
        launch {
            val dao = WordsDatabase(getApplication()).wordsDao()
            val wordsLong = dao.insertAll(*words.toTypedArray())
            var i = 0
            while (i<words.size){
                words[i].uuid = wordsLong[i].toInt()
                i += 1
            }
            showWords(words)
        }

    }

    fun getWordBySearch(word: String){
        launch {
            val words = WordsDatabase(getApplication()).wordsDao().getWordBySearch(word)
            showWords(words)
        }
    }

    fun deleteWords(words: Words){
       launch {
           val dao = WordsDatabase(getApplication()).wordsDao()
           dao.deleteWords(words)
       }
    }
}