package com.example.quran.DAOs

import androidx.room.*
import com.example.quran.Models.HistoricRacine
import com.example.quran.Models.Racine

@Dao
interface HistRacineDao {
    //onConflict = OnConflictStrategy.REPLACE
    @Insert()
    fun insertHistRacine(histRacine: HistoricRacine)

    @Update
    fun updateHistRacine(histRacine: HistoricRacine)

    @Delete
    fun deleteHistRacine(histRacine: HistoricRacine)

    @Query("SELECT * FROM HistoricRacine WHERE idHistRacine == :idHistRacine")
    fun getHistRacineByName(idHistRacine: Int): List<HistoricRacine>

    @Query("SELECT Racine.* FROM  Racine, HistoricRacine WHERE  HistoricRacine.idHistRacine == Racine.idRacine")
    fun getHistRacines(): List<Racine>
}