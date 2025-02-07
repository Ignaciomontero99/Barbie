package com.ignmonlop.barbie.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ignmonlop.barbie.JoyApplication
import com.ignmonlop.barbie.R
import com.ignmonlop.barbie.databinding.ItemJoyBinding
import com.ignmonlop.barbie.models.Joy

class JoyAdapter(private val onFavorite: (Joy) -> Unit) :
    ListAdapter<Joy, JoyAdapter.JoyViewHolder>(JoyDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JoyViewHolder {
        val binding = ItemJoyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JoyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JoyViewHolder, position: Int) {
        holder.bind(getItem(position), onFavorite)
    }

    class JoyViewHolder(private val binding: ItemJoyBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(juguete: Joy, onFavorite: (Joy) -> Unit) {
            binding.tvName.text = juguete.name
            binding.tvPrecio.text = "${juguete.price}€"
            Glide.with(binding.imagePhoto.context)
                .load(juguete.imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.drawable.ic_android)
                .into(binding.imagePhoto)

            // Verificar si el juguete está en favoritos
            val isFavorite = JoyApplication.favoritos.contains(juguete)
            binding.cbFavorite.isChecked = isFavorite

            // Manejar clics en el checkbox
            binding.cbFavorite.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    JoyApplication.agregarFavorito(juguete)
                } else {
                    JoyApplication.eliminarFavorito(juguete)
                }
                onFavorite(juguete)
            }
        }
    }
}



class JoyDiffCallback : DiffUtil.ItemCallback<Joy>(){
    override fun areItemsTheSame(oldItem: Joy, newItem: Joy): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Joy, newItem: Joy): Boolean = oldItem == newItem
}
