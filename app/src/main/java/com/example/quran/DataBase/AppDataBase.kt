package com.example.quran.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quran.DAOs.QuranWordDao
import com.example.quran.DAOs.RacineDao
import com.example.quran.DAOs.SurahDao
import com.example.quran.DAOs.VersetDao
import com.example.quran.Models.QuranWord
import com.example.quran.Models.Racine
import com.example.quran.Models.Surah
import com.example.quran.Models.Verset

@Database(entities = [Racine::class, Surah::class, Verset::class, QuranWord::class,HistoricRacine::class], version = 1)

abstract class AppDataBase : RoomDatabase() {
    abstract fun racineDao(): RacineDao
<<<<<<< HEAD
    abstract fun historicRacineDao(): HistRacineDao
=======
>>>>>>> parent of 01b7046... test commit
    abstract fun quranWordDao(): QuranWordDao
    abstract fun versetDao(): VersetDao
    abstract fun surahDao(): SurahDao

<<<<<<< HEAD
=======

    companion object {
        var INSTANCE: AppDataBase? = null

        fun getAppDataBase(context: Context): AppDataBase? {
            if (INSTANCE == null){
                synchronized(AppDataBase::class){
                    Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, "Test").build()
                    //INSTANCE = Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, "myDB").build()
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, "gkjh46541").createFromAsset("QuranDB.db")
                        .allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }

        /*fun CreateAppDataBase(context: Context) : AppDataBase? {
            synchronized(AppDataBase::class)
        }*/



        fun destroyDataBase(){
            INSTANCE = null
        }
    }
>>>>>>> parent of 01b7046... test commit
}