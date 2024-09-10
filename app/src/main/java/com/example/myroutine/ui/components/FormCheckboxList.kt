package com.example.myroutine.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FormCheckboxList(
    modifier: Modifier = Modifier,
    title: String?,
    options: List<String>,
    optionsSelected: Map<String, Boolean>,
    onOptionsSelectedChange: (String, Boolean) -> Unit
) {
    Column(modifier) {
        title?.let {
            Text(
                text = it,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        options.forEach { day ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked = optionsSelected.getOrDefault(day, false),
                    onCheckedChange = { isChecked -> onOptionsSelectedChange(day, isChecked) }
                )
                Text(text = day)
            }
        }
    }
}