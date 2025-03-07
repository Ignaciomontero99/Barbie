package com.ignmonlop.barbie.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ignmonlop.barbie.models.Joy
import com.ignmonlop.barbie.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class JoyViewModel: ViewModel() {
    private val _juguetes = MutableLiveData<List<Joy>>()
    val juguetes: LiveData<List<Joy>> get() = _juguetes

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun fetchJuguetes() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getJuguetes()
                withContext(Dispatchers.Main){
                    _juguetes.value = response
                    Log.i("JoyViewModel", "Juguetes cargados: ${_juguetes.value}")
                }

            } catch (e: HttpException) {
                _errorMessage.value = "Error en el servidor: ${e.code()} - ${e.message()}"
            } catch (e: IOException) {
                _errorMessage.value = "Error de conexión. Revisa tu internet."
            } catch (e: Exception) {
                _errorMessage.value = "Error desconocido: ${e.localizedMessage}"
            }
        }
    }
}