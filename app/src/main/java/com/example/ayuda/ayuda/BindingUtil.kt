package com.example.ayuda.ayuda

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.ayuda.R
import com.example.ayuda.db.Ayuda
import java.text.SimpleDateFormat

@BindingAdapter("image")
fun ImageView.image(item: Ayuda){
    item?.let{
        setImageResource(
            when(item.medio){
                "Phone" -> R.drawable.ic_baseline_local_phone_24
                "Sms" -> R.drawable.ic_baseline_sms_24
                else -> R.drawable.ic_baseline_email_24
            })
    }
}

@BindingAdapter("fecha")
fun TextView.textViewFecha(item: Ayuda){
    item?.let {
        text= SimpleDateFormat("dd/MM/yyyy").format(item.time).toString()
    }
}

@BindingAdapter("hora")
fun TextView.textViewHora(item: Ayuda){
    item?.let {
        text= SimpleDateFormat("HH:mm:ss").format(item.time).toString()
    }
}