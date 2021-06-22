package com.example.ayuda.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medio_ayuda")
data class Ayuda (
    @PrimaryKey(autoGenerate = true)
    val nro: Long= 0,
    @ColumnInfo
    val medio: String="",
    @ColumnInfo
    val time: Long= System.currentTimeMillis()
)
