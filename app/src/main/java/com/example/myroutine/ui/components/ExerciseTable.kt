package com.example.myroutine.ui.components

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myroutine.R
import com.example.myroutine.data.model.Exercise


@Composable
fun ExerciseTable(context: Context = LocalContext.current, exercises: List<Exercise>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        item {
            ExerciseTableHeader(context)
        }
        item { HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp)) }
        items(exercises.size) { index ->
            ExerciseTableItem(exercises[index])
        }
    }
}

@Composable
private fun ExerciseTableHeader(context: Context) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = context.resources.getString(R.string.my_routine_exercise),
            fontSize = 18.sp,
            modifier = Modifier.weight(1f, fill = true)
        )
        Text(
            text = context.resources.getString(R.string.my_routine_reps),
            fontSize = 18.sp,
            modifier = Modifier.weight(1f, fill = true)
        )
    }
}

@Composable
private fun ExerciseTableItem(exercise: Exercise) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = exercise.name,
            modifier = Modifier.weight(1f, fill = true)
        )
        Text(
            text = exercise.repetitions,
            modifier = Modifier.weight(1f, fill = true)
        )
    }
}