package com.example.myroutine.data.network

import com.example.myroutine.BuildConfig
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Authorization: Bearer ${BuildConfig.API_AUTH_TOKEN}")
    @POST("chat/completions")
    suspend fun generateWorkoutPlan(@Body request: WorkoutRequest): WorkoutResponse
}
