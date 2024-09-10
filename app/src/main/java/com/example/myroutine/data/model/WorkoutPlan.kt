package com.example.myroutine.data.model

data class WorkoutPlan(
    val week: List<DayPlan>
)

data class DayPlan(
    val day: String,
    val minutes: Int,
    val exercises: List<Exercise>
)

data class Exercise(
    val name: String,
    val repetitions: String
)