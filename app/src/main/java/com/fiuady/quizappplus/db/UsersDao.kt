package com.fiuady.quizappplus.db

import androidx.room.*

@Dao
interface UsersDao {
    @Query(" SELECT COUNT(*) FROM users")
     fun getNumber(): Int

     @Insert
     fun insertUser(user: User)
     //@Query("INSERT INTO users(id, nombre) ")
}