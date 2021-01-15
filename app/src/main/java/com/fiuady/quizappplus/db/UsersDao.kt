package com.fiuady.quizappplus.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UsersDao {
    @Query(" SELECT COUNT(*) FROM users")
    fun getNumber(): Int

    @Query("SELECT * FROM users WHERE activo=0")
    fun getUsers(): List<User>

    @Query("SELECT * FROM users WHERE activo = 1")
    fun getActiveUser(): User

    @Query("SELECT * FROM users WHERE nombre = (:usuario)")
    fun getNewUser(usuario: String): User

    @Insert
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("DELETE FROM users WHERE id = (:id)")
    fun deleteById(id: Int)
}