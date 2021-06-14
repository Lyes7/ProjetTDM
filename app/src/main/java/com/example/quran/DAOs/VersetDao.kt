package com.example.quran.DAOs
import androidx.room.*
import com.example.quran.Models.Verset

@Dao
interface VersetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVerset(Verset: Verset)

    @Update
    fun updateVerset(Verset: Verset)

    @Delete
    fun deleteVerset(Verset: Verset)

    @Query("SELECT * FROM Verset WHERE NumAya == :NumAya")
    fun getVersetByNumAya(NumAya: Int): List<Verset>

    @Query("SELECT * FROM Verset")
    fun getVersets(): List<Verset>

    @Query("select Verset.* from Verset JOIN QuranWord on Verset.IdAya == QuranWord.Id_Aya and QuranWord.idRacine == :RacineId")
    fun getVersetsByRacine(RacineId: Int) : List<Verset>

    //select Aya.* from Aya JOIN QuranWord on Aya.Id_Aya = QuranWord.ID_Aya and QuranWord.idRacine = 25
}