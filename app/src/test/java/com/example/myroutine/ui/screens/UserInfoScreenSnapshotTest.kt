package com.example.myroutine.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import app.cash.paparazzi.DeviceConfig.Companion.PIXEL_6
import app.cash.paparazzi.Paparazzi
import com.example.myroutine.data.model.UserInfo
import com.example.myroutine.ui.theme.MyRoutineTheme
import org.junit.Rule
import org.junit.Test

class UserInfoScreenSnapshotTest {

    @get:Rule
    val paparazzi = Paparazzi(deviceConfig = PIXEL_6)

    @Test
    fun screenshotWithoutData() {
        paparazzi.snapshot {
            MyRoutineTheme(applyWindowInsets = false) {
                Box(modifier = Modifier.background(Color.White)) {
                    UserInfoScreen(UserInfo()) {}
                }
            }
        }
    }

    @Test
    fun screenshotWithData() {
        paparazzi.snapshot {
            MyRoutineTheme(applyWindowInsets = false) {
                Box(modifier = Modifier.background(Color.White)) {
                    UserInfoScreen(mockUserInfo) {}
                }
            }
        }
    }

    private val mockUserInfo = UserInfo(
        age = "24",
        weight = "85",
        height = "185",
        selectedGoal = "Fitness",
        hoursPerWeek = "6",
        selectedDaysList = listOf("Monday", "Wednesday", "Friday")
    )
}