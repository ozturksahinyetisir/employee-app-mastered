package com.ozturksahinyetisir.personalappquiz.di

import android.content.Context
import androidx.room.Room
import com.ozturksahinyetisir.personalappquiz.data.AppDatabase
import com.ozturksahinyetisir.personalappquiz.data.EmployeeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app-database").build()
    }

    @Provides
    @Singleton
    fun provideEmployeeDAO(appDatabase: AppDatabase): EmployeeDao {
        return appDatabase.employeeDao()
    }
}