package com.example.myroutine.di

import androidx.room.Room
import com.example.myroutine.data.db.AppDatabase
import com.example.myroutine.data.network.ApiService
import com.example.myroutine.repositories.RoutineRepository
import com.example.myroutine.repositories.RoutineRepositoryImpl
import com.example.myroutine.ui.viewmodels.MyRoutineViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        Retrofit.Builder()
            .baseUrl("https://api.saia.ai/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
    single<RoutineRepository> { RoutineRepositoryImpl(get(), get(), get()) }
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "app_database")
            .build()
    }
    single { get<AppDatabase>().userInfoDao() }
    single { get<AppDatabase>().workoutPlanDao() }
    viewModel { MyRoutineViewModel(get(), get()) }
}
