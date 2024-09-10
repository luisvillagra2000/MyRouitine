package com.example.myroutine.ui.components

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormExposedDropdown(
    label: String,
    options: List<String>,
    optionSelected: String,
    onOptionSelectedChange: (String) -> Unit,
    initExpanded: Boolean = false
) {
    var expanded by remember { mutableStateOf(initExpanded) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        FormOutlinedTextField(
            label = label,
            value = optionSelected,
            onValueChange = onOptionSelectedChange,
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryNotEditable),
            readOnly = true,
        ) { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption) },
                    onClick = {
                        onOptionSelectedChange(selectionOption)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }

        }
    }
}
