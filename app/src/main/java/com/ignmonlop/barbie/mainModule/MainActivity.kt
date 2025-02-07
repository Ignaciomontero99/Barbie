package com.ignmonlop.barbie.mainModule

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ignmonlop.barbie.JoyApplication
import com.ignmonlop.barbie.adapter.JoyAdapter
import com.ignmonlop.barbie.databinding.ActivityMainBinding
import com.ignmonlop.barbie.models.Joy
import com.ignmonlop.barbie.viewModels.JoyViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: JoyViewModel
    private lateinit var adapter: JoyAdapter
    private lateinit var sharedPreferences: SharedPreferences
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("barbie_prefs", MODE_PRIVATE)

        // Configurar RecyclerView con LayoutManager y Adapter
        setupRecyclerView()

        // Cargar los favoritos guardados al iniciar
        val favoritosGuardados = getFavorites()
        JoyApplication.favoritos.clear()
        JoyApplication.favoritos.addAll(favoritosGuardados)

        // Actualizar el adaptador con los favoritos guardados
        adapter.submitList(JoyApplication.favoritos)

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
        if (JoyApplication.favoritos.contains(juguete)) {
            JoyApplication.eliminarFavorito(juguete)
        } else {
            JoyApplication.agregarFavorito(juguete)
        }

        // Guardar los favoritos actualizados en SharedPreferences
        saveFavorites(JoyApplication.favoritos)

        // Asegurar actualización visual en RecyclerView sin causar errores
        val position = adapter.currentList.indexOf(juguete)
        if (position != -1) {
            binding.recyclerView.post {
                adapter.notifyItemChanged(position)
            }
        }
    }

    fun saveFavorites(favorites: List<Joy>) {
        val json = gson.toJson(favorites)
        sharedPreferences.edit().putString("favorites_list", json).apply()
        Log.d("PreferencesManager", "Favoritos guardados: $json")
    }

    fun getFavorites(): MutableList<Joy> {
        val json = sharedPreferences.getString("favorites_list", null)
        Log.d("PreferencesManager", "Recuperando favoritos: $json")
        if (json == null) {
            Log.d("PreferencesManager", "No hay favoritos guardados.")
        }
        val type = object : TypeToken<MutableList<Joy>>() {}.type
        return gson.fromJson(json, type) ?: mutableListOf()
    }
}
