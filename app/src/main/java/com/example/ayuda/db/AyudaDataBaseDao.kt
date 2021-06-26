package com.example.ayuda.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AyudaDataBaseDao {

    @Insert
    fun insert(ayuda: Ayuda)

    @Query("select * from medio_ayuda where nro= :id ")
    fun getAyuda(id:Long): Ayuda

    @Query("select * from medio_ayuda order by time desc")
    fun getAll(): LiveData<List<Ayuda>>

    @Query("delete from medio_ayuda")
    fun deleteAll()
}