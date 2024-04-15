package com.example.quizapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuizDao {


    @Query("SELECT * FROM quiz_result ORDER BY id DESC")
    fun getResult(): LiveData<List<Result>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(result: Result)

    @Query("DELETE FROM quiz_result")
    suspend fun deleteAll()
}