package com.example.quran.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Surah(
    @PrimaryKey()
    val IdSourat: Int ,
    val NomSourat: String ,
    val Location: String ,
    val NbrAyat: Int ,
    val NbrMots: Int ,
    val NbrLettre: Int
)
