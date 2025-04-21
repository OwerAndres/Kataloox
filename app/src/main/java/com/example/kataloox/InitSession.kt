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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onNavigateToRegister: () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorFondo)
    ){
        //Manchas
        Image(
            painter = painterResource(R.drawable.ellipse1),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .offset(x= 250.dp, y= 600.dp)
        )

        Image(
            painter = painterResource(R.drawable.ellipse2),
            contentDescription = null,
            modifier = Modifier
                .size(140.dp)
                .offset(x= 30.dp, y=30.dp)
        )

        Image(
            painter = painterResource(R.drawable.ellipse3),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .offset(x= 30.dp, y= 500.dp)
        )

        Image(
            painter = painterResource(R.drawable.ellipse4),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .offset(x= 300.dp, y= 160.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(R.drawable.logo), contentDescription = "Logo", modifier = Modifier.size(150.dp))

            Text("Kataloox", fontFamily = agbalumo,
                fontSize = 48.sp, fontWeight = FontWeight.Bold,
                color = androidx.compose.ui.graphics.Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Iniciar sesión",fontFamily = adlfont,
                fontSize = 24.sp,fontWeight = FontWeight.Bold,
                color = androidx.compose.ui.graphics.Color.White)

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(value = "", onValueChange = {}, label = { Text("Correo",
                fontFamily = adlfont,
                fontSize = 14.sp,
                color = androidx.compose.ui.graphics.Color.White) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = blancoPRZ,
                    unfocusedBorderColor = blancoPRZ,
                    cursorColor = blancoPRZ,
                    focusedLabelColor = blancoPRZ,
                    unfocusedLabelColor = blancoPRZ
                )
            )
            OutlinedTextField(value = "", onValueChange = {}, label = { Text("Contraseña",
                fontFamily = adlfont,
                fontSize = 14.sp,
                color = androidx.compose.ui.graphics.Color.White) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = blancoPRZ,
                    unfocusedBorderColor = blancoPRZ,
                    cursorColor = blancoPRZ,
                    focusedLabelColor = blancoPRZ,
                    unfocusedLabelColor = blancoPRZ
                ),
                visualTransformation = PasswordVisualTransformation())

            Text(
                "¿Olvidaste la Contraseña?",
                fontFamily = adlfont,
                color = androidx.compose.ui.graphics.Color.White,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { /* Lógica sesión */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorBoton1)) {
                Text("Iniciar sesión",fontFamily = adlfont, fontSize = 20.sp, color = androidx.compose.ui.graphics.Color.Black)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Iniciar sesión con:",fontFamily = adlfont, fontSize = 20.sp,color = androidx.compose.ui.graphics.Color.White)

            OutlinedButton(
                onClick = { /* Google Auth */ },
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = colorBoton1)
            ) {
                Icon(painter = painterResource(R.drawable.googlelogo),
                    contentDescription = null,
                    tint = androidx.compose.ui.graphics.Color.Unspecified,
                    modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Continuar con Google",fontFamily = adlfont, fontSize = 20.sp,color = androidx.compose.ui.graphics.Color.Black)
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = onNavigateToRegister) {
                Text("Regístrate",fontFamily = adlfont, fontSize = 20.sp,color = androidx.compose.ui.graphics.Color.White)
            }
        }


    }
}

@Preview(showSystemUi = true)
@Composable
fun initsession(){
    LoginScreen(){}
}