package com.example.ayuda.ayuda

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.ayuda.db.Ayuda
import com.example.ayuda.db.AyudaDataBaseDao
import com.example.ayuda.formatoAllAyudatoString
import kotlinx.coroutines.*

class AyudaViewModel(private val dataBase: AyudaDataBaseDao, application: Application): AndroidViewModel(application) {

    private val jobViewModel= Job()
    private val uiScope= CoroutineScope(Dispatchers.Main + jobViewModel)
    override fun onCleared() {
        super.onCleared()
        jobViewModel.cancel()
    }

    private var allAyuda= dataBase.getAll()
    val allAyudaString= Transformations.map(allAyuda){formatoAllAyudatoString(it, application.resources)}

//Phone---------------------------------------
    private val _visiblePhone= MutableLiveData<Int>()
    val visiblePhone: LiveData<Int>
        get()=_visiblePhone
    private val _activoPhone= MutableLiveData<Boolean>()
    val activoPhone: LiveData<Boolean>
        get()=_activoPhone
    fun viPhone(){
        _visiblePhone.value=View.GONE
    }
    fun acPhone(){
        _activoPhone.value=false
    }

//Sms----------------------------------------
    private val _visibleSms= MutableLiveData<Int>()
    val visibleSms: LiveData<Int>
        get()=_visibleSms
    private val _activoSms= MutableLiveData<Boolean>()
    val activoSms: LiveData<Boolean>
        get()=_activoSms
    fun viSms(){
        _visibleSms.value=View.GONE
    }
    fun acSms(){
        _activoSms.value=false
    }

//Email---------------------------------------
    private val _visibleEmail= MutableLiveData<Int>()
    val visibleEmail: LiveData<Int>
        get()=_visibleEmail
    private val _activoEmail= MutableLiveData<Boolean>()
    val activoEmail: LiveData<Boolean>
        get()=_activoEmail
    fun viEmail(){
        _visibleEmail.value=View.GONE
    }
    fun acEmail(){
        _activoEmail.value=false
    }

//-------------------------------------------
    fun onPhone(){
        uiScope.launch{
            withContext(Dispatchers.IO){
                var ayuda= Ayuda(medio= medioPhone)
                dataBase.insert(ayuda)
           }
        }
        _activoPhone.value=true
    }
    fun onSms(){
        uiScope.launch {
            withContext(Dispatchers.IO){
                var ayuda= Ayuda(medio = medioSms)
                dataBase.insert(ayuda)
            }
        }
        _activoSms.value=true
    }
    fun onEmail(){
        uiScope.launch {
            withContext(Dispatchers.IO){
                var ayuda=Ayuda(medio = medioEmail)
                dataBase.insert(ayuda)
            }
        }
        _activoEmail.value=true
    }

    fun onDelete(){
        uiScope.launch {
            withContext(Dispatchers.IO){
                dataBase.deleteAll()
            }
        }
    }

    init {
        _visiblePhone.value=View.VISIBLE
        _visibleSms.value=View.VISIBLE
        _visibleEmail.value=View.VISIBLE

        _activoPhone.value=false
        _activoSms.value=false
        _activoEmail.value=false
    }

    companion object {
        private const val medioPhone= "Phone"
        private const val medioSms= "Sms"
        private const val medioEmail= "Email"
    }
}