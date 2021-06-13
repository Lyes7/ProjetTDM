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
}