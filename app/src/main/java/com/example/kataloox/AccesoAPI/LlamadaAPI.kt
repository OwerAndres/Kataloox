package com.example.kataloox

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
/**
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun obtenerUsuarios() {
    var usuarios by remember { mutableStateOf(emptyList<Usuario>()) } // Corrección aquí
    var errorMessage by remember { mutableStateOf<String?>(null) }

    /LaunchedEffect(true) {
        try {
            val result = RetrofitClient.apiService.getUsuarios()
            usuarios = result

            //Mostrar en Logcat
            Log.d("API_RESPONSE", "Usuarios recibidos: ${result.size}")
            result.forEach {
                Log.d("API_RESPONSE", "ID: ${it.id}, Usuario: ${it.usuario}, Email: ${it.email}")
            }

        } catch (e: Exception) {
            errorMessage = "Error al obtener usuarios: ${e.message}"
            Log.e("ErrorAPI", errorMessage ?: "Error desconocido")
        }
    }

    Scaffold(
        modifier = Modifier.padding(24.dp),
        content = {
            if (errorMessage != null) {
                Text("Error: $errorMessage", modifier = Modifier.padding(16.dp))
            } else {
                Column(modifier = Modifier.fillMaxSize()) {
                    usuarios.forEach { usuario ->
                        Text(
                            "Usuario: ${usuario.id} - ${usuario.usuario} - ${usuario.email}",
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    )
}
        **/