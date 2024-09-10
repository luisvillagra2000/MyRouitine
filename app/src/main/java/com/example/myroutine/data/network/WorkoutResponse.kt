package com.example.myroutine.data.network

import com.example.myroutine.data.model.WorkoutPlan
import com.google.gson.Gson

data class WorkoutResponse(
    val choices: List<Choice>
)

data class Choice(
    val message: Message
)


fun WorkoutResponse.toWorkoutPlan(): WorkoutPlan {
    val gson = Gson()
    val stringJSON = this.choices.first().message.content
        .removePrefix("```json")
        .removeSuffix("```")
    val workoutPlan = gson.fromJson(stringJSON, WorkoutPlan::class.java)
    return workoutPlan.copy(week = workoutPlan.week.filter { it.exercises.isNotEmpty() })
}