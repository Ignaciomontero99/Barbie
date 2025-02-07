package com.ignmonlop.barbie.mainModule

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ignmonlop.barbie.R
import com.ignmonlop.barbie.adapter.JoyAdapter
import com.ignmonlop.barbie.databinding.ActivityMainBinding
import com.ignmonlop.barbie.viewModels.JoyViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: JoyViewModel by viewModels()
    private lateinit var adapter: JoyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar RecyclerView con LayoutManager y Adapter
        adapter = JoyAdapter { juguete ->
            // Acción al marcar como favorito
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Observar los datos del ViewModel
        viewModel.juguetes.observe(this) { juguetes ->
            adapter.submitList(juguetes)
        }
        viewModel.fetchJuguetes()

        // FAB para navegar a favoritos
        binding.fab.setOnClickListener {
            val intent = Intent(this, FavoritosActivity::class.java)
            startActivity(intent)
        }

    }
}
