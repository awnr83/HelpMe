package com.example.ayuda.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Ayuda::class], version=2, exportSchema = false)
abstract class AyudaDataBase:RoomDatabase() {
    abstract val ayudaDataBaseDao:AyudaDataBaseDao

    companion object {
        @Volatile
        private var INSTANCE: AyudaDataBase? = null
        fun getInstance(context: Context): AyudaDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AyudaDataBase::class.java,
                        "history_help"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}