package com.fiuady.quizappplus.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update

@Dao
interface SettingsDao {
    @Query("SELECT * FROM settings WHERE userid = (:id)")
    fun getsettings(id: Int): Settings

    @Query("SELECT topicsarray FROM settings WHERE userid = (:id)")
    fun getTopicsarray(id: Int): String

    @Query("UPDATE settings SET dificulty=(:df) WHERE userid=(:id) ")
    fun senddificulty(df:Int,id:Int)

    @Query("UPDATE settings SET hints=(:h) WHERE userid=(:id) ")
    fun sendhints(h:Int,id:Int)

    @Query("UPDATE settings SET hintsquantity=(:hq) WHERE userid=(:id) ")
    fun sendhintsquantity(hq:Int,id:Int)

    @Update
    fun actualizar(Settings:Settings)
}