package com.ozturksahinyetisir.personalappquiz.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employees")
data class Employee(
    @PrimaryKey(autoGenerate = true)
    val newid:Int?,
    val id: Int,
    val employee_age: Int,
    val employee_name: String,
    val employee_salary: Int,
    val profile_image: String
)

data class Employees(
    var status:String?,
    var message:String?,
    var data:List<Employee>?
)
