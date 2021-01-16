package com.fiuady.quizappplus.db

import androidx.room.*


@Dao
interface SettingsDao {
    @Query("SELECT * FROM settings WHERE userid = (:id)")
    fun getsettings(id: Int): Settings

    @Query("INSERT INTO settings (userid) VALUES (:usuarioid)")
    fun insertbyid(usuarioid: Int)

    @Update
   fun actualizar(Settings: Settings)


}