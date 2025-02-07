package com.ignmonlop.barbie

import android.app.Application
import com.ignmonlop.barbie.models.Joy

class JoyApplication : Application() {
    companion object {
        var favoritos = mutableListOf<Joy>()
        private lateinit var instance: JoyApplication // Definir la instancia de la aplicación

        fun agregarFavorito(juguete: Joy) {
            if (!favoritos.contains(juguete)) {
                favoritos.add(juguete)
            }
        }

        fun eliminarFavorito(juguete: Joy) {
            favoritos.remove(juguete)
        }

        fun obtenerFavoritos(): List<Joy> {
            return favoritos
        }
    }
}