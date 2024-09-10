package com.example.myroutine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myroutine.ui.screens.ErrorScreen
import com.example.myroutine.ui.screens.LoadingScreen
import com.example.myroutine.ui.screens.MyRoutineScreen
import com.example.myroutine.ui.screens.UserInfoScreen
import com.example.myroutine.ui.theme.MyRoutineTheme
import com.example.myroutine.ui.viewmodels.MyRoutineUiState
import com.example.myroutine.ui.viewmodels.MyRoutineViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MyRoutineViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyRoutineTheme {
                when (viewModel.myRoutineUiState) {
                    is MyRoutineUiState.Init ->
                        UserInfoScreen((viewModel.myRoutineUiState as MyRoutineUiState.Init).values) {
                            viewModel.uploadUserInfo(it)
                            viewModel.getRoutine(getString(R.string.language))
                        }

                    MyRoutineUiState.Error ->
                        ErrorScreen(
                            onRetry = { viewModel.getRoutine(getString(R.string.language)) },
                            onBack = { viewModel.createNewRoutine() }
                        )

                    MyRoutineUiState.Loading ->
                        LoadingScreen()

                    is MyRoutineUiState.Success ->
                        MyRoutineScreen(workoutPlan = (viewModel.myRoutineUiState as MyRoutineUiState.Success).workoutPlan) {
                            viewModel.createNewRoutine()
                        }
                }
            }
        }
    }
}


