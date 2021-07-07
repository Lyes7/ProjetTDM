package com.example.quran.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quran.DAOs.*
import com.example.quran.Models.*

@Database(entities = [Racine::class, Surah::class, Verset::class, QuranWord::class,HistoricRacine::class], version = 1)

abstract class AppDataBase : RoomDatabase() {
    abstract fun racineDao(): RacineDao
    abstract fun historicRacineDao(): HistRacineDao
    abstract fun quranWordDao(): QuranWordDao
    abstract fun versetDao(): VersetDao
    abstract fun surahDao(): SurahDao

}