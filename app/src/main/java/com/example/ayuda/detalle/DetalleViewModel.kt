package com.example.ayuda.detalle

import android.app.Application
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ayuda.R
import com.example.ayuda.R.drawable.ic_baseline_local_phone_24
import com.example.ayuda.db.Ayuda
import com.example.ayuda.db.AyudaDataBaseDao
import kotlinx.coroutines.*
import java.text.SimpleDateFormat

class DetalleViewModel(private val id: Long, private val dataBase: AyudaDataBaseDao, application: Application):AndroidViewModel(application) {
    private val job= Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)
    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    private val _imgMedioPhone= MutableLiveData<Int>()
    val imgMedioPhone: LiveData<Int>
        get()= _imgMedioPhone
    private val _imgMedioSms= MutableLiveData<Int>()
    val imgMedioSms: LiveData<Int>
        get()= _imgMedioSms
    private val _imgMedioEmail= MutableLiveData<Int>()
    val imgMedioEmail: LiveData<Int>
        get()= _imgMedioEmail
    private val _fecha= MutableLiveData<String>()
    val fecha: LiveData<String>
        get()= _fecha
    private val _hora= MutableLiveData<String>()
    val hora: LiveData<String>
        get()= _hora

    private fun inicializar(){
        uiScope.launch {
            var ayuda= getAyuda()
            _fecha.value= SimpleDateFormat("dd/MM/yyyy").format(ayuda.time).toString()
            _hora.value= SimpleDateFormat("HH:mm:ss").format(ayuda.time).toString()

            //drawable/ic_baseline_local_phone_24
            when(ayuda.medio){
                "Phone" -> _imgMedioPhone.value = View.VISIBLE
                "Sms" -> _imgMedioSms.value = View.VISIBLE
                else -> _imgMedioEmail.value = View.VISIBLE
            }
        }
    }
    private suspend fun getAyuda(): Ayuda {
        return withContext(Dispatchers.IO) {
            var ayuda = dataBase.getAyuda(id)
            ayuda
        }
    }

    init {
        _imgMedioPhone.value= View.GONE
        _imgMedioSms.value= View.GONE
        _imgMedioEmail.value= View.GONE
        inicializar()
    }

}