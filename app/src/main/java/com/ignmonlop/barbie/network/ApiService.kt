package com.ignmonlop.barbie.network

import com.ignmonlop.barbie.models.Joy
import retrofit2.http.GET


interface ApiService {
    @GET("juguetes")
    suspend fun getJuguetes(): List<Joy>
}

