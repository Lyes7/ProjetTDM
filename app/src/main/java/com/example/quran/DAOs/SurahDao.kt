package com.example.quran.DAOs
import androidx.room.*
import com.example.quran.Models.Surah


@Dao
interface SurahDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSurah(Surah: Surah)

    @Update
    fun updateSurah(Surah: Surah)

    @Delete
    fun deleteSurah(Surah: Surah)

    @Query("SELECT * FROM Surah WHERE IdSourat == :IdSourat")
    fun getSurahById(IdSourat: Int): List<Surah>

    @Query("SELECT * FROM Surah")
    fun getSurahs(): List<Surah>
}