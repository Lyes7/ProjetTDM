package com.example.quran.DAOs
import androidx.room.*
import com.example.quran.Models.QuranWord


@Dao
interface QuranWordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQword(QWord: QuranWord)

    @Update
    fun updateQword(Qword: QuranWord)

    @Delete
    fun deleteQword(Qword: QuranWord)

    @Query("SELECT * FROM QuranWord WHERE ID_Word == :ID_Word")
    fun getQwordByName(ID_Word: Int): List<QuranWord>

    @Query("SELECT * FROM QuranWord")
    fun getQwords(): List<QuranWord>

}