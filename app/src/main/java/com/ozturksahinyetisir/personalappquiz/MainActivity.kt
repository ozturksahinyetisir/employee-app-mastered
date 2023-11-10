package com.ozturksahinyetisir.personalappquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ozturksahinyetisir.personalappquiz.di.EmployeeViewModel
import com.ozturksahinyetisir.personalappquiz.navigation.NavigationContainer
import com.ozturksahinyetisir.personalappquiz.network.ComposeResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    @Inject
    lateinit var composeResponse: ComposeResponse
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val employeeViewModel: EmployeeViewModel by viewModels()
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                setContent {
                    navController = rememberNavController()
                    NavigationContainer(navController,composeResponse,employeeViewModel,this@MainActivity)
                }}}
    }
}

