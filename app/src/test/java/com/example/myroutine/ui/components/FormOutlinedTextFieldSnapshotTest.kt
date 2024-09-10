package com.example.myroutine.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import app.cash.paparazzi.DeviceConfig.Companion.PIXEL_6
import app.cash.paparazzi.Paparazzi
import com.example.myroutine.ui.theme.MyRoutineTheme
import org.junit.Rule
import org.junit.Test

class FormOutlinedTextFieldSnapshotTest {

    @get:Rule
    val paparazzi = Paparazzi(deviceConfig = PIXEL_6)

    @Test
    fun screenshot() {
        paparazzi.snapshot {
            MyRoutineTheme(applyWindowInsets = false) {
                Column(modifier = Modifier.background(Color.White)) {
                    FormOutlinedTextField(label = "Label", value = "", onValueChange = {})
                    FormOutlinedTextField(label = "Label", value = "Value", onValueChange = {})
                }
            }
        }
    }
}