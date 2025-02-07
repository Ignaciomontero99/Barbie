package com.ignmonlop.barbie.mainModule

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ignmonlop.barbie.JoyApplication
import com.ignmonlop.barbie.adapter.JoyAdapter
import com.ignmonlop.barbie.databinding.ActivityMainBinding
import com.ignmonlop.barbie.models.Joy
import com.ignmonlop.barbie.viewModels.JoyViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: JoyViewModel by viewModels()
    private lateinit var adapter: JoyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ✅ Configurar RecyclerView con LayoutManager y Adapter
        setupRecyclerView()

        // ✅ Observar los datos del ViewModel
        viewModel.juguetes.observe(this) { juguetes ->
            adapter.submitList(juguetes)
        }
        viewModel.fetchJuguetes()

        // ✅ FAB para navegar a favoritos
        binding.fab.setOnClickListener {
            val intent = Intent(this, FavoritosActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        adapter = JoyAdapter { juguete -> onFavoriteClicked(juguete) }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun onFavoriteClicked(juguete: Joy) {
        // Lógica para agregar/eliminar de favoritos
        if (JoyApplication.favoritos.contains(juguete)) {
            JoyApplication.eliminarFavorito(juguete)
        } else {
            JoyApplication.agregarFavorito(juguete)
        }
        adapter.notifyDataSetChanged()
    }
}