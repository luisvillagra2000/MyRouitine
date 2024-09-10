package com.example.myroutine.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myroutine.R


@Composable
fun ErrorScreen(onRetry: () -> Unit, onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = LocalContext.current.resources.getString(R.string.error_message),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
            Row {
                Button(
                    onClick = { onRetry() },
                    modifier = Modifier
                        .padding(8.dp),
                ) {
                    Text(text = LocalContext.current.resources.getString(R.string.error_retry_button))
                }
                Button(
                    onClick = { onBack() },
                    modifier = Modifier
                        .padding(8.dp),
                ) {
                    Text(text = LocalContext.current.resources.getString(R.string.error_back_button))
                }
            }

        }
    }
}