package com.example.myroutine.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import app.cash.paparazzi.DeviceConfig.Companion.PIXEL_6
import app.cash.paparazzi.Paparazzi
import com.example.myroutine.data.model.DayPlan
import com.example.myroutine.data.model.Exercise
import com.example.myroutine.data.model.WorkoutPlan
import com.example.myroutine.ui.theme.MyRoutineTheme
import org.junit.Rule
import org.junit.Test

class MyRoutineScreenSnapshotTest {

    @get:Rule
    val paparazzi = Paparazzi(deviceConfig = PIXEL_6)

    @Test
    fun screenshot() {
        paparazzi.snapshot {

            MyRoutineTheme(applyWindowInsets = false) {
                Box(modifier = Modifier.background(Color.White)) {
                    MyRoutineScreen(mockWorkoutPlan) {}
                }
            }
        }
    }
}

private val mockWorkoutPlan = WorkoutPlan(
    week = listOf(
        DayPlan(
            day = "Monday",
            minutes = 60,
            exercises = listOf(
                Exercise(name = "Push-ups", repetitions = "3 sets of 15"),
                Exercise(name = "Squats", repetitions = "3 sets of 20")
            )
        ),
        DayPlan(
            day = "Wednesday",
            minutes = 45,
            exercises = listOf(
                Exercise(name = "Pull-ups", repetitions = "3 sets of 10"),
                Exercise(name = "Lunges", repetitions = "3 sets of 12")
            )
        ),
        DayPlan(
            day = "Friday",
            minutes = 50,
            exercises = listOf(
                Exercise(name = "Deadlift", repetitions = "4 sets of 8"),
                Exercise(name = "Bench Press", repetitions = "4 sets of 10")
            )
        )
    )
)