package com.example.ayuda.detalle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.ayuda.R
import com.example.ayuda.databinding.FragmentDetalleBinding
import com.example.ayuda.db.AyudaDataBase

class DetalleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentDetalleBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_detalle, container, false)

        val application= requireNotNull(activity!!).application
        val database= AyudaDataBase.getInstance(application).ayudaDataBaseDao

        val idArg= DetalleFragmentArgs.fromBundle(arguments!!).idAyuda
        val viewModelFactory= DetalleViewModelFactory(idArg, database, application)
        val viewModel= ViewModelProvider(this,viewModelFactory).get(DetalleViewModel::class.java)

        binding.viewModel=viewModel
        binding.lifecycleOwner=this

        return binding.root
    }
}