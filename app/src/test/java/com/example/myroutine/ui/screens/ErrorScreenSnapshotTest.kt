package com.example.myroutine.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import app.cash.paparazzi.DeviceConfig.Companion.PIXEL_6
import app.cash.paparazzi.Paparazzi
import com.example.myroutine.ui.theme.MyRoutineTheme
import org.junit.Rule
import org.junit.Test

class ErrorScreenSnapshotTest {

    @get:Rule
    val paparazzi = Paparazzi(deviceConfig = PIXEL_6)

    @Test
    fun screenshot() {
        paparazzi.snapshot {
            MyRoutineTheme(applyWindowInsets = false) {
                Box(modifier = Modifier.background(Color.White)) {
                    ErrorScreen({}, {})
                }
            }

        }
    }
}