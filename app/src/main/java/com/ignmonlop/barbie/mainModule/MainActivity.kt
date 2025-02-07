package com.ignmonlop.barbie.mainModule

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ignmonlop.barbie.JoyApplication
import com.ignmonlop.barbie.adapter.JoyAdapter
import com.ignmonlop.barbie.databinding.ActivityMainBinding
import com.ignmonlop.barbie.models.Joy
import com.ignmonlop.barbie.viewModels.JoyViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: JoyViewModel
    private lateinit var adapter: JoyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar RecyclerView con LayoutManager y Adapter
        setupRecyclerView()

        viewModel = ViewModelProvider(this)[JoyViewModel::class.java]
        viewModel.fetchJuguetes()


        // Observar los datos del ViewModel
        viewModel.juguetes.observe(this) { juguetes ->
            if (juguetes.isEmpty()) {
                Log.d("MainActivity", "Lista de juguetes vacía")
            } else {
                Log.d("MainActivity", "Juguetes cargados: ${juguetes.size}")
            }
            adapter.submitList(juguetes)
        }

        viewModel.errorMessage.observe(this) {
            Log.e("MainActivity", "Error: $it")
        }


        // FAB para navegar a favoritos
        binding.fab.setOnClickListener {
            val intent = Intent(this, FavoritosActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        adapter = JoyAdapter { juguete -> onFavoriteClicked(juguete) }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        Log.d("RecyclerView", "RecyclerView configurado correctamente")
    }


    private fun onFavoriteClicked(juguete: Joy) {
        // Lógica para agregar/eliminar de favoritos
        if (JoyApplication.favoritos.contains(juguete)) {
            JoyApplication.eliminarFavorito(juguete)
        } else {
            JoyApplication.agregarFavorito(juguete)
        }

        // Usar Handler para hacer la notificación después de un pequeño retraso
        Handler(Looper.getMainLooper()).post {
            val position = adapter.currentList.indexOf(juguete)
            if (position != -1) {
                adapter.notifyItemChanged(position)
            }
        }
    }
}