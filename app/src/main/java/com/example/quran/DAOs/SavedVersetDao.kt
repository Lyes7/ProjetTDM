package com.example.quran.DAOs
import androidx.room.*
import com.example.quran.Models.SavedVerset
import com.example.quran.Models.Verset

@Dao
interface SavedVersetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSavedVerset(savedVerset: SavedVerset)

    @Update
    fun updateSavedVerset(savedVersetId: SavedVerset)

    @Delete
    fun deleteSavedVerset(savedVerset: SavedVerset)

    @Query("DELETE FROM SavedVerset WHERE IdAya == :idAya AND note == :note ")
    fun deleteSavedVersetByNote(idAya:String, note:String)

    @Query("SELECT * FROM SAVEDVERSET WHERE IdSavedAya == :idSavedAya")
    fun getSavedVersetByNumAya(idSavedAya: Int): List<SavedVerset>

    @Query("SELECT * FROM SavedVerset")
    fun getSavedVersets(): List<SavedVerset>


    //select Aya.* from Aya JOIN QuranWord on Aya.Id_Aya = QuranWord.ID_Aya and QuranWord.idRacine = 25
}