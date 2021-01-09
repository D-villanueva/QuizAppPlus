package com.fiuady.quizappplus.db

import androidx.room.*

@Dao
interface UsersDao {
    @Query(" SELECT COUNT(*) FROM users")
    fun getNumber(): Int

    @Query("SELECT * FROM users")
    fun getUsers(): List<User>

    @Query("SELECT * FROM users WHERE activo = 1")
    fun getActiveUser(): User

    @Insert
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)
}