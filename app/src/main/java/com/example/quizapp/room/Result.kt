package com.example.quizapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_result")
class Result (
    @ColumnInfo(name = "result")
    val result: String,

    @ColumnInfo(name = "correct_answers")
    val correctAnswers: Int,

    @ColumnInfo(name = "total_answers")
    val totalAnswers: Int,

    @ColumnInfo(name = "continent")
    val continent: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}