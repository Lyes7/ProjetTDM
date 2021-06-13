package com.example.quran.DAOs

import androidx.room.*
import com.example.quran.Models.Racine

@Dao
interface RacineDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRacine(Racine: Racine)

    @Update
    fun updateRacine(Racine: Racine)

    @Delete
    fun deleteRacine(Racine: Racine)

    @Query("SELECT * FROM Racine WHERE Racine == :Racine")
    fun getRacineByName(Racine: String): List<Racine>

    @Query("SELECT * FROM Racine")
    fun getRacines(): List<Racine>
}