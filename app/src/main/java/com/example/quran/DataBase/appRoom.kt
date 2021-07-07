package com.example.quran.DataBase

import android.app.Application

class appRoom: Application() {
    override fun onCreate() {
        super.onCreate()
        ServiceRoom.context=applicationContext
    }
}