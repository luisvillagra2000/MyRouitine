package com.example.myroutine.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myroutine.R
import com.example.myroutine.data.DataSource
import com.example.myroutine.data.model.UserInfo
import com.example.myroutine.ui.components.FormCheckboxList
import com.example.myroutine.ui.components.FormExposedDropdown
import com.example.myroutine.ui.components.FormOutlinedTextField

@Composable
fun UserInfoScreen(initValues: UserInfo, onSubmit: (UserInfo) -> Unit) {
    val context = LocalContext.current

    var userInfo by remember { mutableStateOf(initValues) }
    val goalsOptions = DataSource.goals.map { context.resources.getString(it) }
    val availableDays = DataSource.days.map { context.resources.getString(it) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = context.resources.getString(R.string.user_info_title),
            fontSize = 24.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        )
        Form(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .weight(1f),
            formContent = userInfo,
            onFormContentChange = { userInfo = it },
            context = context,
            goalsOptions = goalsOptions,
            availableDays = availableDays
        )
        HorizontalDivider()
        Button(
            onClick = {
                onSubmit(userInfo)
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
        ) {
            Text(context.resources.getString(R.string.user_info_submit_button))
        }
    }
}

@Composable
private fun Form(
    modifier: Modifier = Modifier,
    formContent: UserInfo,
    onFormContentChange: (UserInfo) -> Unit,
    context: Context,
    goalsOptions: List<String>,
    availableDays: List<String>
) {
    val selectedDays = remember {
        mutableStateMapOf(*formContent.selectedDaysList.map { (it to true) }.toTypedArray())
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        FormOutlinedTextField(
            value = formContent.age,
            onValueChange = {
                val newValue = it.filter { char -> char.isDigit() }
                    .take(2)
                onFormContentChange(formContent.copy(age = newValue))
            },
            label = LocalContext.current.resources.getString(R.string.user_info_age),
            keyboardType = KeyboardType.Number
        )

        FormOutlinedTextField(
            value = formContent.weight,
            onValueChange = {
                val newValue = it.filter { char -> char.isDigit() }
                onFormContentChange(formContent.copy(weight = newValue))
            },
            label = LocalContext.current.resources.getString(R.string.user_info_weight),
            keyboardType = KeyboardType.Number
        )

        FormOutlinedTextField(
            value = formContent.height,
            onValueChange = {
                val newValue = it.filter { char -> char.isDigit() }
                onFormContentChange(formContent.copy(height = newValue))
            },
            label = context.resources.getString(R.string.user_info_height),
            keyboardType = KeyboardType.Number
        )

        FormExposedDropdown(
            optionSelected = formContent.selectedGoal,
            onOptionSelectedChange = { onFormContentChange(formContent.copy(selectedGoal = it)) },
            label = context.resources.getString(R.string.user_info_goal),
            options = goalsOptions
        )

        FormOutlinedTextField(
            value = formContent.hoursPerWeek,
            onValueChange = {
                val newValue = it.filter { char -> char.isDigit() }
                if (newValue.isBlank() || newValue.toInt() <= 168) {
                    onFormContentChange(formContent.copy(hoursPerWeek = newValue))
                }
            },
            label = LocalContext.current.resources.getString(R.string.user_info_hours_por_week),
            keyboardType = KeyboardType.Number
        )
        FormCheckboxList(
            title = context.resources.getString(R.string.user_info_available_days),
            options = availableDays,
            optionsSelected = selectedDays,
            onOptionsSelectedChange = { day, isChecked ->
                selectedDays[day] = isChecked
                val selectedDaysList = selectedDays.filterValues { it }.keys.toList()
                onFormContentChange(formContent.copy(selectedDaysList = selectedDaysList))
            }
        )
    }
}
