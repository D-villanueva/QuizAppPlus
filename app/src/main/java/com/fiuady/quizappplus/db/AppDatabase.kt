package com.fiuady.quizappplus.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class,Themes::class, Questions::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
}


