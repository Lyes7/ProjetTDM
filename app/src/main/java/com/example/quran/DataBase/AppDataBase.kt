package com.example.quran.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quran.DAOs.*
import com.example.quran.Models.*

@Database(entities = [Racine::class, Surah::class, Verset::class, QuranWord::class], version = 1)

abstract class AppDataBase : RoomDatabase() {
    abstract fun racineDao(): RacineDao
    //abstract fun historicRacineDao(): HistRacineDao
    abstract fun quranWordDao(): QuranWordDao
    abstract fun versetDao(): VersetDao
    abstract fun surahDao(): SurahDao



    companion object {
        var INSTANCE: AppDataBase? = null

        fun getAppDataBase(context: Context): AppDataBase? {
            if (INSTANCE == null){
                synchronized(AppDataBase::class){
                    Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, "Test").build()
                    //INSTANCE = Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, "myDB").build()
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, "database").createFromAsset("TdmQuranDB.db")
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
}