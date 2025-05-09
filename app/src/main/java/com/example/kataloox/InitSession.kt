package com.example.kataloox


import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kataloox.auth.GoogleAuthUiClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextAlign
import com.example.kataloox.AccesoAPI.RegistroGoogle
import com.example.kataloox.AccesoAPI.RetrofitClient
import kotlin.math.log


const val RC_SIGN_IN = 1001

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onNavigateToRegister: () -> Unit) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val googleAuthClient = GoogleAuthUiClient(context)

    var mensaje by remember { mutableStateOf<String?>(null) }
    var nombreUsuario by remember { mutableStateOf<String?>(null) }
    var correoUsuario by remember { mutableStateOf<String?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val data = result.data
        if (result.resultCode == Activity.RESULT_OK && data != null) {
            coroutineScope.launch {
                val isSignedIn = googleAuthClient.signInWithIntent(data)
                if (isSignedIn) {
                    val nombre = googleAuthClient.getSignedInUser()
                    val correo = googleAuthClient.getCorreoUsuario()
                    val usuario = correo?.substringBefore("@") ?: "sin_usuario"

                    nombreUsuario = nombre
                    correoUsuario = correo

                    Log.d("GoogleLogin", "Enviando datos a la API...")
                    try {
                        val response = RetrofitClient.apiService.registrarUsuarioGoogle(
                            RegistroGoogle(
                                nombre = nombre ?: "Sin nombre",
                                usuario = usuario,
                                email = correo ?: "sin@email.com"
                            )
                        )

                        Log.d("GoogleLogin", "Respuesta de API: $response")

                        mensaje = if (response.success) {
                            /**Navegaacion de catalogo**/
                            "Inicio de sesión exitoso con Google"
                        } else {
                            "Error desde el servidor: ${response.error}"
                        }
                    } catch (e: Exception) {
                        Log.e("GoogleLogin", "Error al llamar a la API: ${e.message}", e)
                    }

                } else {
                    Log.d("RegistroGoogle", "Inicio de sesión fallido")
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorFondo)
    ) {
        // Fondos decorativos
        Image(painter = painterResource(R.drawable.ellipse1), contentDescription = null,
            modifier = Modifier.size(120.dp).offset(x = 250.dp, y = 600.dp))
        Image(painter = painterResource(R.drawable.ellipse2), contentDescription = null,
            modifier = Modifier.size(140.dp).offset(x = 30.dp, y = 30.dp))
        Image(painter = painterResource(R.drawable.ellipse3), contentDescription = null,
            modifier = Modifier.size(120.dp).offset(x = 30.dp, y = 500.dp))
        Image(painter = painterResource(R.drawable.ellipse4), contentDescription = null,
            modifier = Modifier.size(120.dp).offset(x = 300.dp, y = 160.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(R.drawable.logo), contentDescription = "Logo",
                modifier = Modifier.size(150.dp))

            Text("Kataloox", fontFamily = agbalumo, fontSize = 48.sp,
                fontWeight = FontWeight.Bold, color = androidx.compose.ui.graphics.Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Iniciar sesión", fontFamily = adlfont, fontSize = 24.sp,
                fontWeight = FontWeight.Bold, color = androidx.compose.ui.graphics.Color.White)
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(value = "", onValueChange = {}, label = {
                Text("Correo", fontFamily = adlfont, fontSize = 14.sp, color = androidx.compose.ui.graphics.Color.White)
            },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = blancoPRZ,
                    unfocusedBorderColor = blancoPRZ,
                    cursorColor = blancoPRZ,
                    focusedLabelColor = blancoPRZ,
                    unfocusedLabelColor = blancoPRZ
                )
            )

            OutlinedTextField(value = "", onValueChange = {}, label = {
                Text("Contraseña", fontFamily = adlfont, fontSize = 14.sp, color = androidx.compose.ui.graphics.Color.White)
            },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = blancoPRZ,
                    unfocusedBorderColor = blancoPRZ,
                    cursorColor = blancoPRZ,
                    focusedLabelColor = blancoPRZ,
                    unfocusedLabelColor = blancoPRZ
                ),
                visualTransformation = PasswordVisualTransformation()
            )

            Text("¿Olvidaste la Contraseña?", fontFamily = adlfont, color = androidx.compose.ui.graphics.Color.White,
                fontSize = 14.sp, modifier = Modifier.padding(top = 4.dp))
            Spacer(modifier = Modifier.height(30.dp))

            Button(onClick = { /* lógica */ }, colors = ButtonDefaults.buttonColors(containerColor = colorBoton1)) {
                Text("Iniciar sesión", fontFamily = adlfont, fontSize = 20.sp, color = androidx.compose.ui.graphics.Color.Black)
            }

            Spacer(modifier = Modifier.height(30.dp))
            Text("Iniciar sesión con:", fontFamily = adlfont, fontSize = 20.sp, color = androidx.compose.ui.graphics.Color.White)

            OutlinedButton(
                onClick = {
                    val signInIntent = googleAuthClient.signInIntent()
                    launcher.launch(signInIntent)
                },
                colors = ButtonDefaults.outlinedButtonColors(containerColor = colorBoton1)
            ) {
                Icon(
                    painter = painterResource(R.drawable.googlelogo),
                    contentDescription = null,
                    tint = androidx.compose.ui.graphics.Color.Unspecified,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Continuar con Google", fontSize = 20.sp, color = androidx.compose.ui.graphics.Color.Black)
            }
        /*
            mensaje?.let {
                Text(
                    text = it,
                    color = androidx.compose.ui.graphics.Color.White,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                )
            }
            */
         
        /*
            // Mostrar nombre y correo después del login
            if (nombreUsuario != null && correoUsuario != null) {
                Text(
                    text = "Nombre: $nombreUsuario\nCorreo: $correoUsuario",
                    color = androidx.compose.ui.graphics.Color.White,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                )
            }
        */
            Spacer(modifier = Modifier.height(20.dp))

            TextButton(onClick = onNavigateToRegister) {
                Text("Regístrate", fontFamily = adlfont, fontSize = 20.sp, color = androidx.compose.ui.graphics.Color.White)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun initsession(){
    LoginScreen(){}
}