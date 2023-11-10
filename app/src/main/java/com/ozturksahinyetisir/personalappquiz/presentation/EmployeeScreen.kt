package com.ozturksahinyetisir.personalappquiz.presentation

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ozturksahinyetisir.personalappquiz.R
import com.ozturksahinyetisir.personalappquiz.di.EmployeeViewModel
import com.ozturksahinyetisir.personalappquiz.model.Employee
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun EmployeeScreen(navController: NavController,employeeViewModel: EmployeeViewModel

) {
    val montBold = FontFamily(Font(R.font.montserrat_bold))
    var roomEmployeeList by remember { mutableStateOf<List<Employee>>(emptyList()) }
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        val backgroundEmployeeList = withContext(Dispatchers.IO) {
            employeeViewModel.getAllEmployees()
        }
        roomEmployeeList = backgroundEmployeeList
    }
            Column() {
                Row(verticalAlignment = Alignment.CenterVertically){
                    Spacer(Modifier.width(5.dp))
                    Text(text = "Ozturk's Employees",
                        fontSize = 20.sp,
                        fontFamily = montBold,
                        color = Color.Magenta
                    )
                    Spacer(Modifier.width(25.dp))
                    Button(modifier = Modifier
                        .padding(end = 20.dp), onClick = { navController.navigate("employee_add_screen") }) {
                        Text("Yeni Kişi Ekle")
                    }
                }
                Column {
                    LazyColumn {
                        items(roomEmployeeList) {employee ->
                            Box(Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center) {
                                Column(verticalArrangement = Arrangement.Center,
                                    modifier = Modifier.fillMaxSize()) {
                                    Spacer(Modifier.height(5.dp))
                                    Row(
                                        Modifier
                                            .fillMaxSize()
                                            .background(Color.LightGray)) {
                                        Text(text = "${employee.id}",
                                            textAlign = TextAlign.Start,
                                            color = Color.Red)
                                        Spacer(Modifier.width(15.dp))
                                        Icon(
                                            imageVector = Icons.Filled.Person,
                                            contentDescription = null,
                                        )
                                        Spacer(Modifier.width(5.dp))
                                        Text(text = employee.employee_name,
                                            textAlign = TextAlign.Center,
                                            fontFamily = montBold)
                                        Spacer(Modifier.weight(1f))
                                        Text(text = "${employee.employee_salary}",
                                            color = Color.Blue,
                                            fontFamily = montBold,
                                            fontSize = 16.sp,
                                        )
                                        Spacer(Modifier.width(3.dp))
                                        Text(text = "₺",
                                            fontFamily = montBold,
                                            fontSize = 20.sp,
                                        )
                                        Spacer(Modifier.width(10.dp))
                                        Text(text="x",
                                            color = Color.Red,
                                            modifier = Modifier.clickable {
                                            CoroutineScope(Dispatchers.IO).launch {
                                                employeeViewModel.deleteEmployee(employee)
                                            }
                                            navController.navigate("employee_screen")
                                            Toast.makeText(context,"Kişi Silindi.",Toast.LENGTH_SHORT).show()
                                            }, fontSize = 20.sp)
                                        Spacer(Modifier.width(15.dp))
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
            }






