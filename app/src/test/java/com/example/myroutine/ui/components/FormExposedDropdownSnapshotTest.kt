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

class FormExposedDropdownSnapshotTest {

    @get:Rule
    val paparazzi = Paparazzi(deviceConfig = PIXEL_6)

    @Test
    fun screenshot() {
        paparazzi.snapshot {
            MyRoutineTheme(applyWindowInsets = false) {
                Column(modifier = Modifier.background(Color.White)) {
                    FormExposedDropdown(
                        optionSelected = "",
                        onOptionSelectedChange = { },
                        label = "Label",
                        options = listOf("Option 1", "Option 2", "Option 3")
                    )
                    FormExposedDropdown(
                        optionSelected = "Option 1",
                        onOptionSelectedChange = { },
                        label = "Label",
                        options = listOf("Option 1", "Option 2", "Option 3")
                    )
                    FormExposedDropdown(
                        optionSelected = "",
                        onOptionSelectedChange = { },
                        label = "Label",
                        options = listOf("Option 1", "Option 2", "Option 3"),
                        initExpanded = true
                    )
                }
            }

        }
    }
}