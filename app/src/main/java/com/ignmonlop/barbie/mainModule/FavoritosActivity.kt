package com.ignmonlop.barbie.mainModule

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ignmonlop.barbie.JoyApplication

import com.ignmonlop.barbie.adapter.JoyAdapter
import com.ignmonlop.barbie.databinding.ActivityFavoritosBinding
import com.ignmonlop.barbie.viewModels.JoyViewModel

class FavoritosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoritosBinding
    private lateinit var adapter: JoyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar RecyclerView
        adapter = JoyAdapter { juguete ->
            // Si el usuario quita el juguete de favoritos, lo eliminamos de la lista
            JoyApplication.eliminarFavorito(juguete)
            adapter.submitList(JoyApplication.obtenerFavoritos().toList())
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Cargar solo los favoritos
        adapter.submitList(JoyApplication.obtenerFavoritos().toList())

        // FAB para volver a la actividad principal
        binding.fab.setOnClickListener {
            finish() // Volver sin usar onBackPressed()
        }
    }
}
