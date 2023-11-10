package com.ozturksahinyetisir.personalappquiz.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employees")
data class Employee(
    @PrimaryKey(autoGenerate = true)
    val newid:Int = 0,

    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "employee_name")
    val employee_name: String,

    @ColumnInfo(name = "employee_age")
    val employee_age: Int,

    @ColumnInfo(name = "employee_salary")
    val employee_salary: Int,

    @ColumnInfo(name = "profile_image")
    val profile_image: String
)
