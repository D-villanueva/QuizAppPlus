package com.fiuady.quizappplus.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class,Themes::class, Questions::class, Settings::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
    abstract fun settingsDao() :SettingsDao
}


