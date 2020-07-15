package com.example.binarchapter6.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.binarchapter6.database.Memo

@Dao
interface MemoDao {

    @Query("SELECT * FROM MEMO")
    fun getAllMemo(): List<Memo>

    @Insert(onConflict = REPLACE)
    fun insert(memo: Memo): Long

    @Update
    fun updateMemo(memo: Memo): Int

    @Delete
    fun deleteMemo(memo: Memo): Int
}