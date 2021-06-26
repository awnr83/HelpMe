package com.example.ayuda.detalle

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ayuda.db.AyudaDataBaseDao

class DetalleViewModelFactory(private val id: Long,
                              private val dataBase: AyudaDataBaseDao,
                              private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetalleViewModel::class.java)){
            return DetalleViewModel(id, dataBase, application) as T
        }
        throw IllegalArgumentException("No se pudo crear el ViewModel")
    }
}