package com.example.ayuda.ayuda

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ayuda.R
import com.example.ayuda.databinding.FragmentAyudaBinding
import com.example.ayuda.db.Ayuda
import com.example.ayuda.db.AyudaDataBase
import java.lang.Exception

class AyudaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAyudaBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_ayuda, container, false)

        val application= requireNotNull(this.activity).application
        val database= AyudaDataBase.getInstance(application).ayudaDataBaseDao

        val viewModelFactory= AyudaViewModelFactory(database, application)
        val viewModel= ViewModelProvider(this, viewModelFactory).get(AyudaViewModel::class.java)
        binding.viewModel=viewModel
        binding.lifecycleOwner=this



        viewModel.activoPhone.observe(viewLifecycleOwner, Observer {
            if(it){
                llamada()
                viewModel.acPhone()
            }
        })
        viewModel.activoSms.observe(viewLifecycleOwner, Observer {
            if(it){
                sms()
                viewModel.acSms()
            }
        })
        viewModel.activoEmail.observe(viewLifecycleOwner, Observer {
            if(it){
                email()
                viewModel.acEmail()
            }
        })

        return binding.root
    }
    private fun llamada(){
        try {
            val i = Intent(Intent.ACTION_CALL, Uri.parse("tel:03425495150"))
            startActivity(i)
        }catch (e: Exception){
            Toast.makeText(this.context,"No se puede realizar la llamada",Toast.LENGTH_SHORT).show()
        }
    }
    private fun email(){
        val email= Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "awnr83@gmail.com", null))
            .putExtra(Intent.EXTRA_SUBJECT,"Ayuda tecnica..")
            .putExtra(Intent.EXTRA_TITLE,"Ayuda tecnica..")
            .putExtra(Intent.EXTRA_TEXT,"Ayuda tecnica..")
        try {
            startActivity(Intent.createChooser(email, "Enviar Mail.."))
        }catch (e: ActivityNotFoundException){
            Toast.makeText(this.context, "No existe cliente para enviar el correo..", Toast.LENGTH_SHORT).show()
        }
    }
    private fun sms(){
        try {
            Toast.makeText(this.context, "Sms enviado...", Toast.LENGTH_LONG).show()
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage("03425495150", null, "Ayuda sms", null, null)

        } catch (e: Exception) {
            Toast.makeText(this.context, "No ", Toast.LENGTH_LONG).show()
                e.printStackTrace()
        }
    }
}