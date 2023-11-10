package com.ozturksahinyetisir.personalappquiz.presentation

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.Room
import com.ozturksahinyetisir.personalappquiz.data.AppDatabase
import com.ozturksahinyetisir.personalappquiz.network.ComposeResponse
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    composeResponse: ComposeResponse,
    activity: ComponentActivity,
    navController:NavController
) {
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        Room.databaseBuilder(activity, AppDatabase::class.java, "app-database").build()
        composeResponse.fetchDataAndDisplay(activity,navController)
        delay(4000)
        isLoading = false
        navController.navigate("employee_screen")
    }

    if (isLoading) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(modifier = Modifier.width(30.dp))
                Spacer(modifier = Modifier.padding(16.dp))
                Text(
                    textAlign = TextAlign.Center,
                    text = "Veriler yükleniyor...",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Blue
                )
            }
        }
    }
}
