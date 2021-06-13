package com.example.quran.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity (foreignKeys = [ForeignKey(entity = Surah::class,
    parentColumns = ["IdSourat"], childColumns = ["IdSourat"]),
    ForeignKey(entity = Racine::class,
        parentColumns = ["idRacine"], childColumns = ["idRacine"])
])
data class QuranWord(
    @PrimaryKey()
    val ID_Word: Int,
    val IdSourat: Int,
    val NumAya: Int,
    val idRacine: Int,
    val ArabicWord: String,
    val englishWord: String
)
