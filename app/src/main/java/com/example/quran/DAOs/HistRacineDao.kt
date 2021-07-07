package com.example.quran.DAOs

import androidx.room.*
import com.example.quran.Models.HistoricRacine
import com.example.quran.Models.Racine

@Dao
interface HistRacineDao {
    //onConflict = OnConflictStrategy.REPLACE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHistRacine(histRacine: HistoricRacine)

    @Update
    fun updateHistRacine(histRacine: HistoricRacine)

    @Query("DELETE FROM HistoricRacine WHERE idRacine == :idHistRacine")
    fun deleteHistRacine(idHistRacine: Int)

    @Query("SELECT * FROM HistoricRacine WHERE idHistRacine == :idHistRacine")
    fun getHistRacineById(idHistRacine: Int): List<HistoricRacine>

    @Query("SELECT Racine.* FROM  Racine, HistoricRacine WHERE  HistoricRacine.idRacine == Racine.idRacine")
    fun getHistRacines(): List<Racine>
}