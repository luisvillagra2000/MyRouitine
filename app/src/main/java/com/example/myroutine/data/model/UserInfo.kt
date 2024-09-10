package com.example.myroutine.data.model

data class UserInfo(
    val age: String = "",
    val weight: String = "",
    val height: String = "",
    val selectedGoal: String = "",
    val hoursPerWeek: String = "",
    val selectedDaysList: List<String> = emptyList()
)