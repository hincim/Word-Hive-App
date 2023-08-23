package com.hakanninc.weatherapp.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hakanninc.weatherapp.domain.model.Words

@Database(entities = arrayOf(Words::class), version = 1)
abstract class WordsDatabase: RoomDatabase() {

    abstract fun wordsDao() : WordsDao

    companion object{

        @Volatile private var instance: WordsDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){

            instance ?: makeDatabase(context).also {
                instance = it
            }
        }
        private fun makeDatabase(context: Context) = Room.databaseBuilder(context.applicationContext,
            WordsDatabase::class.java,"wordsdatabase").build()

    }
}