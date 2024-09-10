package com.example.myroutine.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import app.cash.paparazzi.DeviceConfig.Companion.PIXEL_6
import app.cash.paparazzi.Paparazzi
import com.example.myroutine.data.model.Exercise
import com.example.myroutine.ui.theme.MyRoutineTheme
import org.junit.Rule
import org.junit.Test

class ExerciseTableSnapshotTest {

    @get:Rule
    val paparazzi = Paparazzi(deviceConfig = PIXEL_6)

    @Test
    fun captureScreenshot() {
        paparazzi.snapshot {
            MyRoutineTheme(applyWindowInsets = false) {
                Box(modifier = Modifier.background(Color.White)) {
                    ExerciseTable(
                        context = LocalContext.current,
                        exercises = listOf(
                            Exercise(name = "Push-ups", repetitions = "3 sets of 15"),
                            Exercise(name = "Squats", repetitions = "3 sets of 20")
                        )
                    )
                }
            }

        }
    }
}