package com.example.quran.Models

import androidx.room.*

//indices = [Index(value = ["IdSourat"], unique = true)],
@Entity (foreignKeys = [ForeignKey(entity = Surah::class,
        parentColumns = ["IdSourat"], childColumns = ["IdSourat"])
    ])
data class Verset (
    @PrimaryKey val IdAya: String,
    val IdSourat: Int,
    val NumAya: Int,
    val Text_AR:String,
    val nbMots: Int,
    val ayaIndex: Int
) {


}