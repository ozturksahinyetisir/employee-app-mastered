package com.ozturksahinyetisir.personalappquiz.di

import android.app.Application
import android.content.Context
import com.ozturksahinyetisir.personalappquiz.network.EmployeeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
                return Retrofit.Builder()
                    .baseUrl("https://dummy.restapiexample.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
    }

    @Provides
    @Singleton
    fun provideEmployeeService(retrofit: Retrofit): EmployeeService {
        return retrofit.create(EmployeeService::class.java)
    }
}