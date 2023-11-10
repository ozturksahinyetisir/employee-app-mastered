package com.ozturksahinyetisir.personalappquiz.network

import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.navigation.NavController
import androidx.room.Room
import com.ozturksahinyetisir.personalappquiz.data.AppDatabase
import com.ozturksahinyetisir.personalappquiz.model.Employee
import com.ozturksahinyetisir.personalappquiz.model.Employees
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject


class ComposeResponse @Inject constructor(private val retrofit: Retrofit) {
    lateinit var employeeList: MutableList<Employee>



    fun fetchDataAndDisplay(activity: ComponentActivity,navController: NavController) {
        val employeeService = retrofit.create(EmployeeService::class.java)
        val service = employeeService.getEmployees()
        val appDatabase = Room.databaseBuilder(activity, AppDatabase::class.java, "app-database").build()
        val employeeDao = appDatabase.employeeDao()

        service.enqueue(object : Callback<Employees> {
            override fun onFailure(call: Call<Employees>, t: Throwable) {
                activity.runOnUiThread {
                    Toast.makeText(activity, t.message.toString(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onResponse(call: Call<Employees>, response: Response<Employees>,) {
                if (response.isSuccessful) {
                    employeeList = (response.body()?.data as MutableList<Employee>?)!!

                    Log.e("MainTAG", "onResponse: ${response.body()?.data} + $employeeList")

                    runBlocking(Dispatchers.IO) {
                        launch {
                            val existingEmployee = employeeDao.getEmployeeByIdAndName(1, "Tiger Nixon")

                            if (existingEmployee == null) {
                                runBlocking(Dispatchers.IO) {
                                    launch {
                                        employeeDao.insertEmployees(employeeList)
                                    }
                                }
                            }
                        }
                    }
                } else {
                        navController.navigate("error_screen")
                        }
                    }
        })
    }
}