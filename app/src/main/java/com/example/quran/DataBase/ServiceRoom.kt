package com.example.quran.DataBase

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.room.Room

@SuppressLint("StaticFieldLeak")
object ServiceRoom {
    lateinit var context: Context
    val database by lazy {
         Room.databaseBuilder(context, AppDataBase::class.java,"Qurandb")
            .createFromAsset("TdmQuranDB.db")
            .allowMainThreadQueries()
            .build()
    }
}