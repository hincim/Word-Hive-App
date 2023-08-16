package com.hakanninc.weatherapp.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.hakanninc.weatherapp.domain.model.Words

@Dao
interface WordsDao {

    @Insert
    suspend fun insertAll(vararg words: Words): List<Long>

    @Query("SELECT * FROM words")
    suspend fun getAllWords() : List<Words>

    @Query("SELECT * FROM words where uuid = :wordsId")
    suspend fun getWords(wordsId: Int) : Words

    @Delete
    suspend fun deleteWords(words: Words)
}