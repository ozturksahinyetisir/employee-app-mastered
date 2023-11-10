package com.ozturksahinyetisir.personalappquiz.di

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.room.Room
import com.ozturksahinyetisir.personalappquiz.data.AppDatabase
import com.ozturksahinyetisir.personalappquiz.data.EmployeeDao
import com.ozturksahinyetisir.personalappquiz.model.Employee
import com.ozturksahinyetisir.personalappquiz.network.ComposeResponse
import com.ozturksahinyetisir.personalappquiz.network.EmployeeService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(
    private val employeeDao: EmployeeDao,
    private val employeeService: EmployeeService,
    private val composeResponse: ComposeResponse,
    private val appDatabase: AppDatabase
) : ViewModel() {

    suspend fun getAllEmployees(): List<Employee> {
        return employeeDao.getAllEmployees()
    }

    suspend fun deleteEmployee(employee: Employee) {
        try {
            employeeDao.deleteEmployee(employee)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun insertEmployee(employee: Employee) {
        employeeDao.insertEmployee(employee)
    }

    fun fetchData(activity: ComponentActivity,navController: NavController) {
        viewModelScope.launch {
            Room.databaseBuilder(activity, AppDatabase::class.java, "app-database").build()
            composeResponse.fetchDataAndDisplay(activity = ComponentActivity(),navController,)
        }
    }

}