package com.example.quizapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quizapp.room.Result

@Database(entities = [Result::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun quizDao(): QuizDao

    companion object{
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): AppDatabase{
            if (instance== null)
                instance = Room.databaseBuilder(ctx.applicationContext,AppDatabase::class.java,"Quiz_database")
                    .fallbackToDestructiveMigration()
                    .build()
                return instance as AppDatabase
        }
    }


}