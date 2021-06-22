package com.example.ayuda.ayuda

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.example.ayuda.db.AyudaDataBaseDao
import java.lang.IllegalArgumentException

class AyudaViewModelFactory(private val dataBase: AyudaDataBaseDao,
                            private val application: Application):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AyudaViewModel::class.java)) {
            return AyudaViewModel(dataBase, application) as T
        }
        throw IllegalArgumentException("no se pudo crear el ViewModel")
    }
}