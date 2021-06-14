package com.example.quran.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

    /*(foreignKeys = [ForeignKey(entity = Racine::class,
        parentColumns = ["idRacine"], childColumns = ["idRacine"]),ForeignKey(entity = Verset::class,
    parentColumns = ["IdAya"], childColumns = ["Id_Aya"])
])*/
@Entity()
data class QuranWord(
    @PrimaryKey()
    val ID_Word: Int,
    val Id_Aya : String,
    val idRacine: Int,
    val ArabicWord: String,
    val englishWord: String
)
