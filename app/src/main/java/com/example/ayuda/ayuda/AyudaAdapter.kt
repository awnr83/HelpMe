package com.example.ayuda.ayuda

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ayuda.databinding.ListItemBinding

import com.example.ayuda.db.Ayuda

class AyudaAdapter(val clickListener: AyudaClickListener): ListAdapter<Ayuda, AyudaAdapter.ViewHolder>(AyudaDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.render(getItem(position), clickListener)
    }

    class ViewHolder private constructor(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun render(item: Ayuda, clickListener: AyudaClickListener){
            binding.ayuda=item
            binding.clickListener= clickListener
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup):ViewHolder{
                var layoutInflater= LayoutInflater.from(parent.context)
                val binding= ListItemBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }
    }

    class AyudaDiffCallBack: DiffUtil.ItemCallback<Ayuda>() {
        override fun areItemsTheSame(oldItem: Ayuda, newItem: Ayuda): Boolean {
            return oldItem.nro==newItem.nro
        }

        override fun areContentsTheSame(oldItem: Ayuda, newItem: Ayuda): Boolean {
            return oldItem==newItem
        }

    }

}

class AyudaClickListener(val clickListener: (AyudaId: Long)->Unit) {
    fun onClick(ayuda: Ayuda)= clickListener(ayuda.nro)
}

