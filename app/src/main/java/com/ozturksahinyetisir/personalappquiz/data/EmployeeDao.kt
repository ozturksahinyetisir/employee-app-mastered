package com.ozturksahinyetisir.personalappquiz.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ozturksahinyetisir.personalappquiz.model.Employee

@Dao
interface EmployeeDao  {
    @Query("SELECT * FROM employees")
    fun getAllEmployees(): List<Employee>

    @Query("SELECT * FROM employees WHERE id = :id AND employee_name = :name")
    fun getEmployeeByIdAndName(id: Int, name: String): Employee?

    @Insert // Tek bir personel eklemek için
    fun insertEmployee(employee: Employee)

    @Insert // Personel listesi eklemek için.
    fun insertEmployees(employees: List<Employee>)

    @Delete
    fun deleteEmployee(employee: Employee)

}