package com.example.quran.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity ()
    //foreignKeys = [ForeignKey(entity = Verset::class,parentColumns = ["verset"], childColumns = ["verset"])])
data class Racine (
    @PrimaryKey()
    val idRacine:Int
    ,val Racine:String
    ,val NBLettre: Int

)

