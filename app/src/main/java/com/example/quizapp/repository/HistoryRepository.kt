package com.example.quizapp.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.quizapp.room.QuizDao
import com.example.quizapp.room.Result

class HistoryRepository(private val quizDao : QuizDao) {

    //Room executes all Queries on a separate thread
    //Observed LiveDAta will notify the observer when the data has changed
    val allResult : LiveData<List<Result>> = quizDao.getResult()

    @WorkerThread
    suspend fun insert(result: Result){
        quizDao.insert(result)
    }

    @WorkerThread
    suspend fun deleteAll(){
        quizDao.deleteAll()
    }
}