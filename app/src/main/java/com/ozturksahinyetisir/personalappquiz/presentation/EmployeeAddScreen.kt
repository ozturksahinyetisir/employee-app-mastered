package com.ozturksahinyetisir.personalappquiz.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ozturksahinyetisir.personalappquiz.di.EmployeeViewModel
import com.ozturksahinyetisir.personalappquiz.model.Employee
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeeAddScreen(navController: NavController,employeeViewModel: EmployeeViewModel) {
    var employeeName by remember { mutableStateOf("") }
    var employeeAge by remember { mutableStateOf(0) }
    var employeeSalary by remember { mutableStateOf(0) }
    var employeeId by remember { mutableStateOf(0) }
    val context = LocalContext.current
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = employeeId.toString(),
            onValueChange = { employeeId = it.toIntOrNull() ?: 0 },
            label = { Text("Employee Id") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = employeeName,
            onValueChange = { employeeName = it },
            label = { Text("Employee Name") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = employeeAge.toString(),
            onValueChange = { employeeAge = it.toIntOrNull() ?: 0 },
            label = { Text("Employee Age") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = employeeSalary.toString(),
            onValueChange = { employeeSalary = it.toIntOrNull() ?: 0 },
            label = { Text("Employee Salary") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val newEmployee = Employee(
                    id = employeeId,
                    employee_name  = employeeName,
                    employee_age = employeeAge,
                    employee_salary = employeeSalary,
                    profile_image = "",
                    newid = null
                )
                CoroutineScope(Dispatchers.IO).launch {
                    employeeViewModel.insertEmployee(newEmployee)
                }
                Toast.makeText(context,"Kişi Eklendi.",Toast.LENGTH_SHORT).show()
            }
        ) {
            Text("Add Employee")
        }
        Button(
            onClick = {
                navController.navigate("employee_screen")
            }
        ) {
            Text("Listeyi Görüntüle")
        }
    }
}