package com.ozturksahinyetisir.personalappquiz.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ErrorScreen(navController : NavController) {
    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("429 | Çok fazla istek atıldı.",
            fontSize = 20.sp,
            color = Color.Red
            )
        Button(onClick = { navController.navigate("splash_screen") }) {
            Text("Tekrardan dene")
        }
        Button(onClick = { navController.navigate("employee_screen") }) {
            Text("Liste ekranına git.")
        }

    }
}