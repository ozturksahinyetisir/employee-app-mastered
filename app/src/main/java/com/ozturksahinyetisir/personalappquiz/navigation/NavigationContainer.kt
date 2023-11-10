package com.ozturksahinyetisir.personalappquiz.navigation

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ozturksahinyetisir.personalappquiz.di.EmployeeViewModel
import com.ozturksahinyetisir.personalappquiz.network.ComposeResponse
import com.ozturksahinyetisir.personalappquiz.presentation.EmployeeAddScreen
import com.ozturksahinyetisir.personalappquiz.presentation.EmployeeScreen
import com.ozturksahinyetisir.personalappquiz.presentation.ErrorScreen
import com.ozturksahinyetisir.personalappquiz.presentation.SplashScreen


@Composable
fun NavigationContainer(navController:NavHostController,
                        composeResponse: ComposeResponse,
                        employeeViewModel: EmployeeViewModel,
                        activity: ComponentActivity
                        ){
    NavHost(navController = navController, startDestination = "splash_screen" ){
        composable("splash_screen"){
            SplashScreen(composeResponse,activity,navController)
        }
        composable("employee_screen"){
            EmployeeScreen(navController,employeeViewModel)
        }
        composable("employee_add_screen"){
            EmployeeAddScreen(navController,employeeViewModel)
        }
        composable("error_screen"){
            ErrorScreen(navController)
        }
    }
}

