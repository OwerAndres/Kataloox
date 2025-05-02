package com.example.kataloox.AccesoAPI

import retrofit2.http.Body
import retrofit2.http.POST


data class RegistroRequest(
    val usuario: String,
    val email: String,
    val psw: String
)

data class RegistroResponse(
    val success: Boolean,
    val error: String? = null
)

interface ApiService {
    @POST("registerAPI.php")
    suspend fun registrarUsuario(@Body request: RegistroRequest): RegistroResponse
}
