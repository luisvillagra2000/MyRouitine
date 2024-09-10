package com.example.myroutine.repositories

import com.example.myroutine.data.model.UserInfo
import com.example.myroutine.data.model.WorkoutPlan
import com.example.myroutine.data.network.ApiService
import com.example.myroutine.data.network.Message
import com.example.myroutine.data.network.WorkoutRequest
import com.example.myroutine.data.network.toWorkoutPlan

interface RoutineRepository {
    suspend fun getRoutine(userInfo: UserInfo): WorkoutPlan
}

class RoutineRepositoryImpl(private val apiService: ApiService) : RoutineRepository {
    override suspend fun getRoutine(userInfo: UserInfo): WorkoutPlan {
        val requestContent = """
                Generate a personalized workout plan based on the following details: 
                age is ${userInfo.age}, 
                height is ${userInfo.height},
                weight is ${userInfo.weight},
                the goal is ${userInfo.selectedGoal}
                and wants to work out for ${userInfo.hoursPerWeek} hours per week.
                The preferred workout days are ${userInfo.selectedDaysList.joinToString(", ")}.
                The response must be in JSON format with the following structure: an array called 'week' that contains 7 objects, one for each day of the week.
                Each object should have a 'day' (name of the day), a 'minutes' (duration of the routine) and an array of 'exercises'.
                Each 'exercise' has two properties: 'name' (name of the exercise) and 'repetitions' (number of set and repetitions).
                On the workout days, include exercises that match the person's goals and physical stats, with appropriate intensity and duration.
                For non-workout days, simply leave the 'exercises' array empty.
                Just respond with that JSON, do not include more dialogue in your response.
            """.trimIndent()

        val request = WorkoutRequest(
            model = "openai/gpt-4o",
            messages = listOf(
                Message(role = "user", content = requestContent)
            )
        )

        return apiService.generateWorkoutPlan(request).toWorkoutPlan()

    }
}
