package com.example.quran.Models

import androidx.room.*

@Entity (foreignKeys = [ForeignKey(entity = Verset::class,
        parentColumns = ["IdAya"], childColumns = ["IdAya"])
    ])
data class SavedVerset (
    @PrimaryKey(autoGenerate = true)
    val IdSavedAya:Int,
    val IdAya: String,
    val note: String

) {

}