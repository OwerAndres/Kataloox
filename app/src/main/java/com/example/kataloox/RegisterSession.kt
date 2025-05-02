package com.example.kataloox

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kataloox.AccesoAPI.RegistroRequest
import com.example.kataloox.AccesoAPI.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(onNavigateToLogin: () -> Unit) {

    var nombre by remember { mutableStateOf("") }
    var usuario by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var confirmarContrasena by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf<String?>(null) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorFondo)
    ) {
        //Manchas
        Image(
            painter = painterResource(R.drawable.ellipse1),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .offset(x = 250.dp, y = 600.dp)
        )

        Image(
            painter = painterResource(R.drawable.ellipse2),
            contentDescription = null,
            modifier = Modifier
                .size(140.dp)
                .offset(x = 30.dp, y = 30.dp)
        )

        Image(
            painter = painterResource(R.drawable.ellipse3),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .offset(x = 30.dp, y = 500.dp)
        )

        Image(
            painter = painterResource(R.drawable.ellipse4),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .offset(x = 300.dp, y = 160.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(R.drawable.logo), contentDescription = "Logo", modifier = Modifier.size(150.dp))

            Text("Kataloox", fontFamily = agbalumo,fontSize = 32.sp, fontWeight = FontWeight.Bold, color = androidx.compose.ui.graphics.Color.White)

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(value = nombre, onValueChange = {nombre = it}, label = { Text("Nombre",
                fontFamily = adlfont,
                fontSize = 14.sp,
                color = androidx.compose.ui.graphics.Color.White) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = blancoPRZ,
                    unfocusedBorderColor = blancoPRZ,
                    cursorColor = blancoPRZ,
                    focusedLabelColor = blancoPRZ,
                    unfocusedLabelColor = blancoPRZ
                ))
            OutlinedTextField(value = usuario, onValueChange = {usuario = it}, label = { Text("Usuario",
                fontFamily = adlfont,
                fontSize = 14.sp,
                color = androidx.compose.ui.graphics.Color.White) },         colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = blancoPRZ,
                unfocusedBorderColor = blancoPRZ,
                cursorColor = blancoPRZ,
                focusedLabelColor = blancoPRZ,
                unfocusedLabelColor = blancoPRZ
            ))
            OutlinedTextField(value = correo, onValueChange = {correo = it}, label = { Text("Correo",
                fontFamily = adlfont,
                fontSize = 14.sp,
                color = androidx.compose.ui.graphics.Color.White) },         colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = blancoPRZ,
                unfocusedBorderColor = blancoPRZ,
                cursorColor = blancoPRZ,
                focusedLabelColor = blancoPRZ,
                unfocusedLabelColor = blancoPRZ
            ))
            OutlinedTextField(value = contrasena, onValueChange = {contrasena = it}, label = { Text("Contraseña",
                fontFamily = adlfont,
                fontSize = 14.sp,
                color = androidx.compose.ui.graphics.Color.White) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = blancoPRZ,
                unfocusedBorderColor = blancoPRZ,
                cursorColor = blancoPRZ,
                focusedLabelColor = blancoPRZ,
                unfocusedLabelColor = blancoPRZ
            ),visualTransformation = PasswordVisualTransformation())
            OutlinedTextField(value = confirmarContrasena, onValueChange = {confirmarContrasena = it}, label = { Text("Confirmar Contraseña",
                fontFamily = adlfont,
                fontSize = 14.sp,
                color = androidx.compose.ui.graphics.Color.White) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = blancoPRZ,
                unfocusedBorderColor = blancoPRZ,
                cursorColor = blancoPRZ,
                focusedLabelColor = blancoPRZ,
                unfocusedLabelColor = blancoPRZ
            ),visualTransformation = PasswordVisualTransformation())

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {

                if(nombre.isEmpty() || usuario.isEmpty() || correo.isEmpty() || contrasena.isEmpty() || confirmarContrasena.isEmpty()){
                    mensaje = "Ningun campo puede estar vacio"
                    return@Button
                }
                if(contrasena != confirmarContrasena){
                    mensaje = "Las contraseñas no coinciden"
                    return@Button
                }
                /**Despues de realizar todas las validaciones accede a la API**/
                CoroutineScope(Dispatchers.IO).launch {
                        try {
                            val response = RetrofitClient.apiService.registrarUsuario(
                                RegistroRequest(usuario, correo, contrasena)
                            )
                            withContext(Dispatchers.Main){
                                if (response.success){
                                    mensaje = "Registro exitoso"
                                }else{
                                    mensaje = "Error: ${response.error}"
                                }
                            }
                        }catch (e: Exception){
                            withContext(Dispatchers.Main){
                                mensaje = "Error de red: ${e.message}"
                            }
                        }
                }
            },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorBoton1))
            {
                Text("Registro",
                    fontFamily = adlfont,
                    fontSize = 20.sp,
                    color = androidx.compose.ui.graphics.Color.Black)
            }

            mensaje?.let {
                Text(it,
                    color = androidx.compose.ui.graphics.Color.Red,
                    modifier = Modifier.padding(top = 8.dp),
                    fontSize = 14.sp,
                    fontFamily = adlfont)
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = onNavigateToLogin) {
                Text("Iniciar sesión",
                    fontFamily = adlfont,
                    fontSize = 20.sp,
                    color = androidx.compose.ui.graphics.Color.White)
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun previewRegister(){
    RegisterScreen(){}
}