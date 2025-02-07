package com.ignmonlop.barbie.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ignmonlop.barbie.models.Joy
import com.ignmonlop.barbie.network.RetrofitClient
import kotlinx.coroutines.launch

class JoyViewModel: ViewModel() {
    private val _juguetes = MutableLiveData<List<Joy>>()
    val juguetes: LiveData<List<Joy>> get() = _juguetes

    fun fetchJuguetes() {
        viewModelScope.launch {
            _juguetes.value = RetrofitClient.apiService.getJuguetes()
        }
    }
}