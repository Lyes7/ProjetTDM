package com.example.quran.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity (foreignKeys = [ForeignKey(entity = Surah::class,
        parentColumns = ["IdSourat"], childColumns = ["IdSourat"])
    ])
data class Verset (
    @PrimaryKey val IdAya: String,
    val IdSourat: Int,
    val NumAya: Int,
    val Text_AR:String,
    val nbMots: Int
) {



}