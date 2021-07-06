package com.example.quran.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity ()
   data class HistoricRacine(
    val idRacine: Int
   ) {
    @PrimaryKey(autoGenerate = true)
    var idHistRacine:Int? = null
}

