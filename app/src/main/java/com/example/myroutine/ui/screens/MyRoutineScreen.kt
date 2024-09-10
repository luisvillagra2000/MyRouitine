package com.example.myroutine.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myroutine.R
import com.example.myroutine.data.model.Exercise
import com.example.myroutine.data.model.WorkoutPlan
import com.example.myroutine.ui.components.ExerciseTable

@Composable
fun MyRoutineScreen(
    workoutPlan: WorkoutPlan,
    newRoutineAction: () -> Unit
) {
    val context = LocalContext.current
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = workoutPlan.week.map { it.day }

    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = context.resources.getString(R.string.my_routine_title),
                fontSize = 24.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            )
            Button(
                onClick = newRoutineAction,
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(context.resources.getString(R.string.my_routine_new_button))
            }
        }
        ScrollableTabRow(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            selectedTabIndex = selectedTabIndex,
            edgePadding = 0.dp
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(text = title) }
                )
            }
        }
        ExerciseTable(context, getExercises(selectedTabIndex, workoutPlan))
    }
}


fun getExercises(index: Int, workoutPlan: WorkoutPlan): List<Exercise> {
    return workoutPlan.week[index].exercises
}