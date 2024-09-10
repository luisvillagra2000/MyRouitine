package com.example.myroutine.di

import com.example.myroutine.data.network.ApiService
import com.example.myroutine.repositories.RoutineRepository
import com.example.myroutine.repositories.RoutineRepositoryImpl
import com.example.myroutine.ui.viewmodels.MyRoutineViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://api.saia.ai/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
    single<RoutineRepository> { RoutineRepositoryImpl(get()) }
    viewModel { MyRoutineViewModel(get()) }
}
