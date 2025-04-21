package com.example.kataloox

import retrofit2.http.GET


interface ApiService {
    @GET("API.php")
    suspend fun getUsuarios(): List<usuario>
}
