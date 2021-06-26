package com.example.ayuda

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.example.ayuda.db.Ayuda
import java.lang.StringBuilder
import java.text.SimpleDateFormat

fun formatoAllAyudatoString(ayuda: List<Ayuda>, resources: Resources): Spanned {

    val sb=StringBuilder()
    sb.apply {
        ayuda.forEach {
            append(resources.getString(R.string.f_fecha))
            append(" <b>${convertLongToDateString(it.time)} </b>\t")
            append(resources.getString(R.string.f_medio))
            append("<b> ${it.medio} </b><br>")
        }
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}
@SuppressLint("SimpleDateFormat")
fun convertLongToDateString(systemTime: Long): String {
    return SimpleDateFormat("dd/MM/yy")
        .format(systemTime).toString()
}
@SuppressLint("SimpleDateFormat2")
fun convertLongToDateString2(systemTime: Long): String {
    return SimpleDateFormat("HH:mm")
        .format(systemTime).toString()
}