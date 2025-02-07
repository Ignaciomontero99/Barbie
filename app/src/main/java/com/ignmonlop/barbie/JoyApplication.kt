package com.ignmonlop.barbie

import android.app.Application
import com.ignmonlop.barbie.models.Joy

class JoyApplication: Application() {
    companion object{
        val favoritos = mutableListOf<Joy>()
        val todosJuguetes = mutableListOf<Joy>()

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

        fun obtenerJuguetes(): MutableList<Joy> {
            return todosJuguetes
        }
    }
}