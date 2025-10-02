package com.example.perfil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.perfil.ui.theme.PerfilTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PerfilTheme {
                PantallaPerfil()
            }
        }
    }
}

@Composable
fun PantallaPerfil(){
    var nombre by remember { mutableStateOf("Carlos")}
    var correo by remember { mutableStateOf("ca.moil@duocuc.cl")}
    var bio by remember { mutableStateOf("Estudiante de Ingeniería en Informática DUOCUC.")}
    var ciudad by remember { mutableStateOf("Puerto Montt")}
    var guardado by remember { mutableStateOf(false)}
    var error by remember { mutableStateOf(false)}

    //Surface

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xCDD4D94F)

    ) {

        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.perfil),
                contentDescription = "Foto de perfil",
                modifier = Modifier.size(120.dp).clip(CircleShape).background(Color.Gray)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text("Mi perfil", fontSize = 24.sp, color = Color(0xFF0C0C0C))

            //Linea divisible
            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            //Campos con borde de linea
            OutlinedTextField(
                value = nombre,
                onValueChange = {nombre = it},
                label = {Text("Nombre: ")},
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))


            OutlinedTextField(
                value = correo,
                onValueChange = {correo = it},
                label = {Text("Correo: ")},
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))


            OutlinedTextField(
                value = bio,
                onValueChange = {bio = it},
                label = {Text("Biografía: ")},
                singleLine = true,
                modifier = Modifier.fillMaxWidth().height(100.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))


            OutlinedTextField(
                value = ciudad,
                onValueChange = {ciudad = it},
                label = {Text("Ciudad: ")},
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                          if (nombre.isNotEmpty() && correo.isNotEmpty() && bio.isNotEmpty() && ciudad.isNotEmpty()){
                              guardado = true
                          }else{
                              error = true
                          }
                          },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1A1A1A),
                    contentColor = Color.White
                )
            ) {
                Text("Guardar cambios del Perfil")
            }
            Spacer(modifier = Modifier.height(7.dp))
            Button(
                onClick = {guardado = false;nombre="";correo="";ciudad="";bio=""},
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
            ) {
                Text("Limpiar formulario")
            }
            //Mensaje de confirmacion
            LaunchedEffect(guardado) {
                if (guardado) {
                    kotlinx.coroutines.delay(3000)
                    guardado = false
                }
            }
            if (guardado){
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Cambios guardados correctamente",
                    color = Color(0xFF2E7D32),
                    fontSize = 28.sp)
            }
            LaunchedEffect(error) {
                if (error) {
                    kotlinx.coroutines.delay(3000)
                    error = false
                }
            }
            if(error){
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Complete todos los campos!",
                    color = Color(0xFFFF0C0C),
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PerfilTheme {
        PantallaPerfil()
    }
}